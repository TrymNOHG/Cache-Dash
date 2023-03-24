<template>
  <div class="map">
    <l-map ref="map" v-model:zoom="zoom" :center="[65.589353,13.501048]">
      <l-tile-layer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          layer-type="base"
          name="OpenStreetMap"
      ></l-tile-layer>
      <l-marker v-for="(coordinate, index) in coordinates" :key="index" :lat-lng="coordinate"></l-marker>
    </l-map>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import {LMap, LTileLayer, LMarker} from "@vue-leaflet/vue-leaflet";

export default {
  components: {
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
    };
  },
};
</script>

<style scoped>
.map {
  height: 400px;
}
</style>
