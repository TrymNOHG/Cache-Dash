<template>
  <div class="recentAdded-window">
    <div class="label">
      <h3>{{ $t('recentItems') }}</h3>
    </div>
    <div class="recent-list">
      <div v-for="item in items" key="listingView.id" class="item-list">
        <router-link :to="{name : 'itemView', params : { name: this.categoryName.toString(), id: item.itemId}}" class="link">
          <ItemThumbnail :item="item"/>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import ItemThumbnail from "@/components/basicInputComponents/ItemThumbnail.vue";
import {useCategoryStore} from "@/store/store";
import {computed} from "vue";

export default {
  name: "mainpageRecentylyAdded",
  components: {ItemThumbnail},

  setup() {

    const store = useCategoryStore();
    const mainCategories = computed(() => {
      return store.getMainCategories;
    });

    store.fetchMainCategories()

    return {
      mainCategories
    }
  }

}
</script>

<style scoped>

  .recentAdded-window{
    display: grid;
    grid-template-rows: 1fr 4fr;
  }

  .recent-list{
    background-color: #7EB09B;
    opacity: 70%;
  }
  .label{
    background-color: white;
    text-align: left;
    font-family: sans-serif;
    font-size: xx-large;
  }

</style>