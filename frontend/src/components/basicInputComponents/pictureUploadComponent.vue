<template>
  <div>
    <div
        v-for="(image, index) in imageList"
        :key="index"
        class="image-input"
        :style="{ 'background-image': `url(${image})` }"
    >
    </div>
    <div
        class="image-input"
        @click="chooseImage"
    ><span
        v-if="imageList.length >= 0"
        class="placeholder"
    >
        Choose an Image
      </span>
      <input
          class="file-input"
          ref="fileInput"
          type="file"
          @input="onSelectFile"
          multiple
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "pictureUploadComponent",

  data () {
    return {
      imageList: []
    }
  },

  methods: {
    chooseImage () {
      this.$refs.fileInput.click()
    },

    async onSelectFile() {
      const input = this.$refs.fileInput
      const files = input.files
      if (files) {
        for (let i = 0; i < files.length; i++) {
          const reader = new FileReader()
          const loadedData = await new Promise((resolve) => {
            reader.onload = (e) => resolve(e.target.result)
            reader.readAsDataURL(files[i])
          })
          this.imageList.push(loadedData)
          console.log(loadedData)
          console.log(this.imageList.at(i))
          console.log(files[i])
          this.$emit('send-image-data', files[i])
        }
      }
    }
  }
};

</script>

<style scoped>
.image-input {
  display: block;
  width: 200px;
  height: 200px;
  cursor: pointer;
  background-size: cover;
  background-position: center center;
}

.placeholder{
  background: #F0F0F0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #333;
  font-size: 18px;
}

.placeholder:hover{
  background: #E0E0E0
}

.file-input{
  display: none
}

input{
  background-color: grey;
}
</style>
