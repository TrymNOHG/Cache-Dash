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
              :value="conversation"
              :key="conversation.username1"
              @click="changeConversation(conversation.username2)"
          >
            {{ conversation.username }}
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
    <div class="chat-window">
      <div v-for="message in conversations.messages" :key="message.id" class="message">
        <div class="message-sender">{{ message.sender }}</div>
        <div class="message-body">{{ message.body }}</div>
      </div>
      <div class="message-input">
        <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message...">
        <button @click="sendMessage(newMessage)">Send</button>
      </div>
    </div>
  </div>
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import {loadConversations, newChat} from "@/services/ChatService";
import {useLoggedInStore} from "@/store/store";

export default {
  components: {BasicInput},

  setup(){
    const store = useLoggedInStore();
    let conversations = [];

    store.fetchUser()

    console.log(store.getUser.data.username);

    loadConversations(store.getUser.data.username)
        .then(response => {
          console.log(response)
          conversations = response;
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
      currentConversation: null
    };
  },
  methods: {
    sendMessage() {
      sendMessage({
        
      })
    },

    changeConversation(username) {
      // code to change conversation
    },

    addNewConversation(bool) {
      this.newConversationBoolean = bool;
    },

    newConversation(username){
      try {
        newChat({
          message: "Conversation Created",
          username1: this.store.getUser.data.username,
          username2: username
        });
      }catch (e){
        this.error.errorBool = true;
        this.error.errorMessage = e;
      }
    },
  },
};
</script>

<style>
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