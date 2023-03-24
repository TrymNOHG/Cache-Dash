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
        color="#41b782"
        :fill="true"
        :fillOpacity="0.5"
        fillColor="#41b782"
      />
      <l-marker v-for="(coordinate, index) in coordinates" :key="index" :lat-lng="coordinate"></l-marker>
    </l-map>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import {LMap, LTileLayer, LMarker, LPolygon} from "@vue-leaflet/vue-leaflet";
import {useCountyStore} from "@/store/store";
import axios from "axios";
import fylker from "@/assets/fylker.json"


export default {
  components: {
    LPolygon,
    LMarker,
    LMap,
    LTileLayer,
  },
  data() {
    return {
      zoom: 4,
      coordinates: [
        [60, 10],
        [51.507222, -0.1275],
        [35.689722, 139.691667],
      ],
      polygonCoordinates: [],
      fylkeData: fylker,
    };
  },

  mounted() {

  },
  methods: {

  }
};
</script>

<style scoped>
.map {
  height: 400px;
}
</style>
