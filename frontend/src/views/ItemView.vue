<template>
  <div class="item-window">
    <ImageCarousel class="image-carousel" />
    <ItemDescription class="item-description" :item="this.item"/>
    <SellerInformation class="seller-information"/>
    <div class="map"></div>
  </div>
</template>

<script>
import ImageCarousel from "@/components/basicInputComponents/ImageCarousel.vue";
import ItemDescription from "@/components/pagesComponents/ItemPage/ItemDescriptionComponent.vue";
import SellerInformation from "@/components/pagesComponents/ItemPage/SellerInformationComponent.vue";

export default {
  name: "item",
  components: {SellerInformation, ItemDescription, ImageCarousel},
  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>
<style scoped>

.item-window{
  display: grid;
  grid-template-rows: 2fr 3fr 1fr;
  grid-template-columns: 1fr 3fr 2fr 1fr;
}

  .image-carousel{
    height: 100%;
    grid-column: 2 / 4;
    grid-row: 1;
    border: black solid 1px;
    width: 100%;
  }

  .item-description{
    grid-row: 2;
    grid-column: 2/3;
  }
  .seller-information{
    grid-row: 2 / 4;
    grid-column: 3;
  }

</style>
