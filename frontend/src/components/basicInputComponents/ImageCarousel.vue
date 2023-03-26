<template>
  <!-- template based on https://www.w3schools.com/howto/howto_js_slideshow.asp -->
  <div class="container">
    <div v-for="(picture,index) in pictures" :picture="picture" key="listingView.id" class="slide">
      <div class="picture">
        <img id="ItemPreview" src="" alt="picture">>
        <div class="numPictures"> {{`${index}/${pictures.length}`}} </div>
      </div>
    </div>
    <IconArrow
        class="prev"
        onclick="changeSlides(-1)"
        direction="left"
    />
    <IconArrow
        class="next"
        onclick="changeSlides(1)"
        direction="right"
    />
  </div>
</template>

<script>
import IconArrow from "@/components/icons/IconArrow.vue";

export default {
  name: "imageCarousel",
  components: {IconArrow},
  props: {
    pictures: {
      type: Array,
      required: true
    },
  },
  data(){
    return{
      slideIndex: 0,
      currentPicture: null
    }
  },
  methods: {

    changeSlides(n) {
      this.slideIndex = (this.slideIndex + n) % this.pictures.length
      this.currentPicture = this.pictures[this.slideIndex];
    }
  }
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
