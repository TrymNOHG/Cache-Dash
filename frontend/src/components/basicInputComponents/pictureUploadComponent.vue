<template>
  <div class="upload">
    <div class="surroundingArea">
      <input
          name=""
          id=""
          type="file"
          multiple @change="handleFileUpload"
      />
      <div v-if="files.length > 0" >
        <h3>Uploaded Files:</h3>
        <ul>
          <li v-for="(file, index) in files" :key="index">{{ file.name }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "pictureUploadComponent",
  data() {
    return{
      files: []
    }
  },
  methods: {
    handleFileUpload(event) {
      const files = event.target.files;
      this.files = [...this.files, ...files];
      // Do something with the files, such as sending them to a server
    }
  }
}
</script>

<style scoped>
.upload {
  margin-bottom: 50px;
  width: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
}
.upload .surroundingArea {
  width: 100%;
  min-height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px dashed #ccc;
  margin-top: 40px;
}
</style>