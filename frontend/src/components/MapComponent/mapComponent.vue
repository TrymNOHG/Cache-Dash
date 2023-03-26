<template>
  <div class="map">
    <l-map ref="map" v-model:zoom="zoom" :center="[65.589353,13.501048]">
      <l-tile-layer
          url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
          layer-type="base"
          name="OpenStreetMap"
      ></l-tile-layer>
      <l-polygon
          :lat-lngs="polygonCoordinates"
          color="blue"
          :fill="false"
      />
      <l-marker
          v-for="(coordinate, index) in coordinatesToShow"
          :key="index"
          :lat-lng="coordinate"
          v-if="coordinatesToShow.length"
      />
    </l-map>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer, LMarker, LPolygon } from "@vue-leaflet/vue-leaflet";
import fylker from "@/assets/fylker.json";
import { booleanPointInPolygon, polygon } from "@turf/turf";

export default {
  components: {
    LPolygon,
    LMarker,
    LMap,
    LTileLayer,
  },

  props: {
    chosenCounty: String,
    itemCoordinates: Array,
  },

  data() {
    return {
      zoom: 4,
      coordinatesToShow: [],
      polygonCoordinates: []
    };
  },

  watch: {
    chosenCounty: function (newVal) {
      if (newVal === 'None'){
        this.loadAll();
      } else {
        this.loadCounty();
      }
    },

    itemCoordinates: async function(allItems){
      console.log("hallo")
      this.coordinatesToShow = this.itemCoordinates;
    }
  },

  mounted() {
    this.coordinatesToShow = this.itemCoordinates;
  },

  methods: {
    loadCounty() {
      this.coordinatesToShow = [];
      fylker.features.forEach((feature) => {
        if (feature.properties.navn[0].navn === this.chosenCounty) {
          this.polygonCoordinates = feature.geometry.coordinates[0];
          this.showMarkersInPolygon();
        }
      });
    },

    loadAll(){
      this.coordinatesToShow = this.itemCoordinates;
      this.polygonCoordinates = [];
    },

    showMarkersInPolygon() {
      for (let i = 0; i < this.itemCoordinates.length; i++) {
        if (booleanPointInPolygon(this.itemCoordinates[i], polygon([this.polygonCoordinates]))){
          this.coordinatesToShow.push(this.itemCoordinates[i])
        }
      }
    },
  },
};
</script>

<style scoped>
.map {
  height: 400px;
}
</style>
