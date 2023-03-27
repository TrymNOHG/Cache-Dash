<template>
  <div class="page-layout">
    <div class="sidebar" style="overflow-y: auto">
      <div class="choose-grid" >
        <div class="message-header">
          <div @click="this.addNewConversation(false)" class="choose">Conversations</div>
          <div @click="this.addNewConversation(true)" class="choose">New Conversation</div>
        </div>
        <ul v-if="!newConversationBoolean" class="conversationList">
          <li class="conversation"
              v-for="conversation in conversations"
              :value="conversation.talkingBuddy"
              :key="conversation.id"
              @click="changeConversation(conversation.talkingBuddy)"
          >
            {{ conversation.talkingBuddy }}
          </li>
        </ul>
        <div v-else class="new-conversation">
          <label>Search For User</label>
          <BasicInput
              id="usernameInput"
              type="username"
              v-model="searchUsername"
              autocomplete="username"
          />
          <button @click="newConversation(searchUsername)">Start Conversation</button>
          <p v-if="error.errorBool" class="Error">User Not Found</p>
        </div>
      </div>
    </div>


    <div class="chat-window" v-if="currentConversation !== null" style="overflow-y: auto">
      <div v-for="message in currentConversation.messages" :key="message.id" class="message">
        <div v-if="message.username === currentConversation.user" class="message-sender">{{ currentConversation.user }}</div>
        <div v-else style="font-weight: bold;">{{currentConversation.talkingBuddy}}</div>
        <div class="message-body">{{ message.message }}</div>
      </div>


      <div class="message-input">
        <input v-model="newMessage" @click="sendMessage" placeholder="Type your message...">
        <button @click="sendMessage({
        conversationId: currentConversation.conversationId,
        username: this.store.getUser.data.username,
        message: newMessage
        })"
        >Send</button>
      </div>
    </div>
  </div>
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import {deleteConversationId, loadConversations, newChat, sendMessage} from "@/services/ChatService";
import {useLoggedInStore} from "@/store/store";
import {ref} from 'vue';

export default {
  components: {BasicInput},

  setup(){
    const store = useLoggedInStore();
    const conversations = ref([]);
    let conversation= {
      conversationId: null,
      user: '',
      talkingBuddy: '',
      messages: [],
    }
    store.fetchUser()
    const user = store.getUser.data;

    loadConversations(user.username)
        .then(response => {
          console.log(response.data)
          changeConversations(response.data);
        }).catch(err => {
      console.log(err)
    });

    function changeConversations(convos){
      for (let i = 0; i < convos.length; i++) {
        if (user.username === convos[i].username1){
          conversation = {
            conversationId: convos[i].conversationId,
            user: convos[i].username1,
            talkingBuddy: convos[i].username2,
            messages: convos[i].messages,
          }
          conversations.value.push(conversation)
        } else {
          conversation = {
            conversationId: convos[i].conversationId,
            talkingBuddy: convos[i].username1,
            user: convos[i].username2,
            messages: convos[i].messages,
          }
          conversations.value.push(conversation)
        }
      }
      console.log(conversations)
    }

    return{
      store,
      user,
      conversations,
    }
  },

  data() {
    return {
      newConversationBoolean: false,
      searchUsername: '',
      messages: [],
      error: {
        errorBool: false,
        errorMessage: ''
      },
      newMessage: '',
      currentConversation: null,
      tempConversations: [],
    };
  },

  methods: {

    sendMessage(MessageObject) {
      let tempConversation = null
      sendMessage({
        conversationId: MessageObject.conversationId,
        username: MessageObject.username,
        message: MessageObject.message
      })
          .then(() => {
            loadConversations(this.store.getUser.data.username)
                .then(response => {
                  tempConversation = response.data.find(conversation => conversation.conversationId === MessageObject.conversationId);
                    if (this.user.username === tempConversation.username1){
                      this.currentConversation = {
                        conversationId: tempConversation.conversationId,
                        user: tempConversation.username1,
                        talkingBuddy: tempConversation.username2,
                        messages: tempConversation.messages,
                      }
                    } else {
                      this.currentConversation = {
                        conversationId: tempConversation.conversationId,
                        talkingBuddy: tempConversation.username1,
                        user: tempConversation.username2,
                        messages: tempConversation.messages,
                      }
                  }
                })
                .catch(err => {
                  console.log(err);
                });
          })
          .catch(err => {
            console.log(err);
          });
      },

    changeConversation(username) {
      for (let i = 0; i < this.conversations.length; i++) {
        if (this.conversations[i].talkingBuddy === username) {
          this.currentConversation = this.conversations[i];
          break;
        }
      }
    },

    addNewConversation(bool) {
      this.newConversationBoolean = bool;
    },

    changeConversations(convos){
      let conversation= {
        conversationId: null,
        user: '',
        talkingBuddy: '',
        messages: [],
      }
      for (let i = 0; i < convos.length; i++) {
        if (this.user.username === convos[i].username1){
          conversation = {
            conversationId: convos[i].conversationId,
            user: convos[i].username1,
            talkingBuddy: convos[i].username2,
            messages: convos[i].messages,
          }
          this.tempConversations.push(conversation)
        } else {
          conversation = {
            conversationId: convos[i].conversationId,
            talkingBuddy: convos[i].username1,
            user: convos[i].username2,
            messages: convos[i].messages,
          }
          this.tempConversations.push(conversation)
        }
      }
    },

    async newConversation(username) {
      try {
        await newChat({
          message: "Conversation Created",
          username1: this.store.getUser.data.username,
          username2: username
        });
        this.addNewConversation(false);
        this.error.errorBool = false;

        loadConversations(this.user.username)
            .then(response => {
              console.log(response.data)
              this.changeConversations(response.data);
            }).catch(err => {
          console.log(err)
        });

        this.conversations = this.tempConversations;

      } catch (e) {
        this.error.errorBool = true;
        this.error.errorMessage = e;
      }
    },


    async deleteConversation(){
      await deleteConversationId({
        conversationId: this.currentConversation.conversationId
      });
    },

  },
  computed: {
    async conversationList() {
      return this.conversations;
    }
  }
};
</script>

<style>

.page-layout {
  display: grid;
  grid-template-columns: 1fr 2fr;
}

.chat-window{
  max-height: 500px;
}

label {
  font-weight: bold;
}

.delete-button{
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
}

.delete-button {
  position: absolute;
  top: 0;
  right: 0;
  background-color: white;
  color: black;
  width: 25px;
  height: 25px;
  border-radius: 5px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
}

.delete-button:hover {
  position: absolute;
  top: 0;
  right: 0;
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
  border-radius: 5px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
}

.new-conversation {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
  text-align: center;
}

button {
  background-color: #3f9293;
  padding: 10px;
  margin-top: 20px;
  border-radius: 5px;
  color: white;
  font-weight: bold;
  width: 150px;
}

button:hover{
  background-color: #4c9fdb;
}

.choose{
  margin: 5px;
  color: black;
  height: 25px;
  width: 200px;
  font-weight: bold;
}

.choose:hover{
  background-color: #55a4e4;
  border-radius: 10px;
}

.message-header {
  display: flex;
  justify-content: center;
  flex-direction: row;
}

.choose-grid{
  display: grid;
  grid-template-rows: 1fr 5fr;
}

.page-layout{
  display: grid;
  grid-template-columns: 2fr 5fr;
  max-height: 100%;
}

.chat-window {
  border-left: #818b96 solid 2px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
  overflow-y: scroll;
}

.message {
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
}

.message-sender {
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.message-body {
  background-color: #f1f1f1;
  padding: 0.5rem;
  border-radius: 0.5rem;
  max-width: 50%;
}

.message-body:last-child {
  margin-left: auto;
}

.message-input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: auto;
}

.message-input input {
  flex: 1;
  padding: 1rem;
  border: none;
  border-bottom: 2px solid #2c3e50;
  font-size: 1.2rem;
}

.message-input button {
  padding: 1rem;
  border: none;
  border-radius: 0.5rem;
  background-color: #2c3e50;
  color: white;
  font-size: 1.2rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.message-input button:hover {
  background-color: #34495e;
}

.conversationList {
  color: black;
  display: flex;
  justify-content: center;
  flex-direction: column;
}

li{
  background-color: #3f9293;
  padding: 10px;
  margin: 10px;
  border-radius: 5px;
  color: white;
  font-weight: bold;
}

li:hover{
  background-color: #4c9fdb;
}

@media (max-width: 768px) {
  .page-layout {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .sidebar {
    flex: 0 0 auto;
    height: auto;
    overflow: visible;
  }

  .chat-window {
    flex: auto;
    margin-top: 20px;
    height: 100%;
  }

  .conversationList {
    max-height: 150px;
    overflow-y: scroll;
  }
}
</style>
