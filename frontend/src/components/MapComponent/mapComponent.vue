<template>
  <div class="map">
    <l-map ref="map" v-model:zoom="zoom" :center="[65.589353,13.501048]">
      <l-tile-layer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
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
  },

  data() {
    return {
      zoom: 4,
      coordinates: [
        [60, 10],
        [51.507222, -0.1275],
        [35.689722, 139.691667],
      ],
      coordinatesToShow: [],
      polygonCoordinates: []
    };
  },

  watch: {
    chosenCounty: function (newVal) {
      console.log("fyleValgt changed:", newVal);
      this.loadCounty();
    },
  },

  mounted() {
    this.coordinatesToShow = this.coordinates;
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

    showMarkersInPolygon() {


      for (let i = 0; i < this.coordinates.length; i++) {
        if (booleanPointInPolygon(this.coordinates[i], polygon([this.polygonCoordinates]))){
          this.coordinatesToShow.push(this.coordinates[i])
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
