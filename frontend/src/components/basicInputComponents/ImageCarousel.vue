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
import {useRoute} from "vue-router";
import {ref} from "vue";
import {loadListingByItemId} from "@/services/ItemService";

export default {
  name: "imageCarousel",
  components: {IconArrow},
  props: {
    itemId: {
      type: Number,
      required: true
    },
  },
  setup(props) {
    const item = ref(null)

    loadListingByItemId(props.itemId).then(response => {
      const {itemId, username, briefDesc, fullDesc,
        address, county, categoryId, price,
        listingStatus, thumbnail, keyInfoList} = response.data

      item.value = {itemId, username, briefDesc, fullDesc,
        address, county, categoryId, price,
        listingStatus, thumbnail, keyInfoList}
    }).catch(error => {
      console.log('error: ', error)
    })

    return {
      item
    }

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
