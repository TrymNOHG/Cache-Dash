<template>
  <div class="filter-window">
    <map-component :chosenCounty="chosenCounty" :itemCoordinates="coordsLit"/>
    <div>
      <h2>Search for county</h2>
        <BasicSelect
            class="dropDown"
            :options="countyStore.allCounties"
            v-model="chosenCounty"
            @change="updateCounty(); updateItemCoordinates();"
            label="Choose a county"
          />
    </div>
  </div>
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";
import BasicSelect from "@/components/basicInputComponents/BasicSelect.vue";
import {useCountyStore, useItemStore} from "@/store/store";
import MapComponent from "@/components/MapComponent/mapComponent.vue";
import BasicRadioGroup from "@/components/basicInputComponents/BasicRadioGroup.vue";
import {computed} from "vue";

export default {
  name: "filterComponent",
  components: {BasicRadioGroup, MapComponent, BasicSelect, BasicCheckbox, BasicInput},

  props: {
    categoryId: {
      type: Number,
      required: true
    },
    categoryName: {
      type: String,
      required: true
    }
  },

  data(){
    return{
      chosenCounty: '',
      categories:[],
      coordsLit: [],
    }
  },

  setup(props){
    const countyStore = useCountyStore();
    const store = useItemStore();
    const items = computed(() => {
      return store.getItems;
    });

    store.fetchItemsByCategoryId(props.categoryId);


    return{
      countyStore,
      store,
      items
    }
  },

  methods: {
    updateCounty() {
      this.$emit("update:ChooseCounty", this.ChooseCounty);
    },

    updateItemCoordinates() {
      this.$emit("update:itemCoordinates", this.itemCoordinates)
    },

    findAddressByLatLng(address) {
      const url = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodeURIComponent(address)}.json?access_token=pk.eyJ1IjoidG9tYWJlciIsImEiOiJjbGZsYmw0Ym0wMDNqM3BvMXNlZ213bXlvIn0.XAO9MuoT6FoiYXnbznnJqg`;
      fetch(url)
          .then(response => response.json())
          .then(data => {
            this.coordsLit.push([data.features[0].center[1], data.features[0].center[0]])
            console.log(this.coordsLit)
          })
          .catch(error => console.error(error));
    }
  },
  watch: {
    ChooseCounty: function (newVal) {
      this.$emit("update:ChooseCounty", newVal);
    },

    itemCoordinates: function (newVal) {
      this.$emit("update:itemCoordinates", newVal)
    },

    items: function(newItems) {
      newItems.forEach(item => {
        this.findAddressByLatLng(item.address);
      });
    }
  },
}
</script>

<style scoped>


  .filter-window{
    display: grid;
    grid-template-rows: 5fr 1fr 1fr 1fr ;
    background-color: #7EB09B;
    opacity: 70%;
    height: 100%;
  }

  button {
    border-width: 2px;
    border-color: black;
    padding: 10px 20px;
    background-color: #FFD700;
    color: black;
    margin: 10px;
    height: 30%;
    width: 50%;
    border-radius: 5px;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
    -2px -2px 4px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -1px);
    transition: all 0.1s ease-in-out;
  }

  button:active {
    background-color: white;
    color: #1E293B;
  }

  button:hover {
    box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
    -4px -4px 8px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -2px);
    color: #1E293B !important;
  }
</style>