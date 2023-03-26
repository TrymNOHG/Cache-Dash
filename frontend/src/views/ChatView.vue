<template>
  <div class="page-layout">
    <div class="sidebar">
      <div class="choose-grid">
        <div class="message-header">
          <div @click="this.addNewConversation(false)" class="choose">Conversations</div>
          <div @click="this.addNewConversation(true)" class="choose">New Conversation</div>
        </div>
        <ul v-if="!newConversationBoolean" class="conversationList">
          <li class="conversation"
              v-for="conversation in conversations"
              :value="conversation.username2"
              :key="conversation.conversationId"
              @click="changeConversation(conversation.username2)"
          >
            <!--<div class="remove" @click="removeConversation(conversation.conversationId)">X</div>-->
            {{ conversation.username2 }}
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
    <div class="chat-window" v-if="currentConversation !== null">
      <div v-for="message in currentConversation.messages" :key="message.id" class="message">
        <div class="message-sender">{{ currentConversation.username2 }}</div>
        <div class="message-body">{{ message.message }}</div>
      </div>
      <div class="message-input">
        <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message...">
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
import {loadConversations, newChat, sendMessage} from "@/services/ChatService";
import {useLoggedInStore} from "@/store/store";
import {ref} from 'vue';

export default {
  components: {BasicInput},

  setup(){
    const store = useLoggedInStore();
    const conversations = ref([]);

    store.fetchUser()

    loadConversations(store.getUser.data.username)
        .then(response => {
          conversations.value = response.data;
        }).catch(err => {
      console.log(err)
    });

    return{
      store,
      conversations
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
      reciver: '',
    };
  },
  methods: {
    sendMessage(MessageObject) {
      sendMessage({
        conversationId: MessageObject.conversationId,
        username: MessageObject.username,
        message: MessageObject.message
      })
          .then(() => {
            loadConversations(this.store.getUser.data.username)
                .then(response => {
                  this.currentConversation = response.data.find(conversation => conversation.conversationId === MessageObject.conversationId);
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
      for (let i = 0; i < this.conversationList.length; i++) {
        if (this.conversationList[i].username2 === username) {
          this.currentConversation = this.conversationList[i];
          break;
        }
      }
    },

    addNewConversation(bool) {
      this.newConversationBoolean = bool;
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

        const conversationsResponse = await loadConversations(this.store.getUser.data.username);
        this.conversations = conversationsResponse.data;
      } catch (e) {
        this.error.errorBool = true;
        this.error.errorMessage = e;
      }
    },

    /*
    async removeConversation(conversationId){
      console.log(conversationId)
      await deleteConversationId({
        conversationId: conversationId
      });
    }

     */
  },
  computed: {
    conversationList() {
      return this.conversations;
    }
  }
};
</script>

<style>

.remove {
  color: black;
  background-color: #d3d3d6;
  width: 25px;
  height: 25px;
  float: right;
  solid-color: black;
  border-radius: 2px;

}

.remove:hover{
  background-color: #a8a8ab;
}


label {
  font-weight: bold;
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
  grid-template-rows: 1fr 10fr;
}

.page-layout{
  display: grid;
  grid-template-columns: 2fr 5fr;
}

.chat-window {
  border-left: #818b96 solid 2px;
  display: flex;
  flex-direction: column;
  height: 100%;
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
</style>
