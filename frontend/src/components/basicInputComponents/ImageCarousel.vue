<template>
  <!-- template based on https://www.w3schools.com/howto/howto_js_slideshow.asp -->
  <div class="container" v-if="pictures">
    <div class="slides">
      <div v-for="(picture, index) in pictures" v-bind:key="index">
        <img v-bind:src="picture" alt="Slide" width="200" height="200">
      </div>
    </div>
    <IconArrow
        class="prev"
        v-on:click="changeSlides(-1)"
        direction="left"
    />
    <IconArrow
        class="next"
        v-on:click="changeSlides(1)"
        direction="right"
    />
  </div>
</template>

<script>
import IconArrow from "@/components/icons/IconArrow.vue";
import {ref} from "vue";
import {loadImagesByItemId} from "@/services/ItemService";

export default {
  name: "imageCarousel",
  components: {IconArrow},
  props: {
    itemId: {
      type: Number,
      required: true
    },
  },
  methods: {
    changeSlides(n) {
      this.slideIndex = (this.slideIndex + n) % this.pictures.length
      this.currentPicture = this.pictures[this.slideIndex];
    }
  },
  setup(props) {
    const pictures = ref([])
    function convertImageBackToUrl(image) {
      return `data:image/png;base64,${image}`;
    }

    loadImagesByItemId(props.itemId).then(response => {
      const listOfDecodedPictures = []
      for(let i = 0; i < response.data.pictures.length; i++) {
        listOfDecodedPictures.push(convertImageBackToUrl(response.data.pictures[i]))
      }
      pictures.value = listOfDecodedPictures
    }).catch(error => {
      console.log('error: ', error)
    })

    return {
      pictures
    }

  },
  data(){
    return{
      slideIndex: 0,
      currentPicture: null
    }
  },
}
</script>

<style scoped>
* {box-sizing:border-box}

 .container{
   position: relative;
   margin: auto;
}

 /*
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}
*/
.prev {
  position: absolute;
  top: 50%;
  left: 0;
  transform: translateY(-50%);
  z-index: 1;
}

.next {
  position: absolute;
  top: 50%;
  right: 0;
  transform: translateY(-50%);
  z-index: 1;
}

.numPictures{
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

</style>
