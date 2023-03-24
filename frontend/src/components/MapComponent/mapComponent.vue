<template>
  <MapboxMap
      id="mapbox"
      access-token="pk.eyJ1IjoidG9tYWJlciIsImEiOiJjbGZsYmw0Ym0wMDNqM3BvMXNlZ213bXlvIn0.XAO9MuoT6FoiYXnbznnJqg"
      map-style="mapbox://styles/mapbox/streets-v12"
      @mb-created="(mapInstance) => map = mapInstance"
      :center="startPos"
      :zoom="startZoom"
      :attributionControl="false"
  />
</template>

<script>
import { MapboxMap } from '@studiometa/vue-mapbox-gl';

export default {
  components: {
    MapboxMap
  },

  data(){
    return{
      map: null,
      startPos: [8.888237, 64.5783089],
      startZoom: 3
    }
  }
}

</script>

<style>

#mapbox{
  max-height: 300px;
}

</style>
