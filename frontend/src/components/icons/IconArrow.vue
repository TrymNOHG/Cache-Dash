<!-- Inspiration from https://freefrontend.com/css-arrows/ -->
<template>
  <div class="arrow" v-show="direction === 'right'">
    <span class="right" ></span>
    <span class="right" ></span>
    <span class="right" ></span>
  </div>
  <div class="arrow" v-show="direction === 'left'">
    <span class="left" ></span>
    <span class="left" ></span>
    <span class="left" ></span>
  </div>
</template>

<script>
export default {
  name: "IconArrow",
  props: {
    direction:{
      type: String,
      default: '',
    }
  }
}
</script>

<style scoped>
body {
  background-color: #322B2B;
}


.arrow {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  cursor: pointer;
}

.right{
  transform: rotate(270deg);
}

.left {
  transform: rotate(90deg);
}

.arrow span {
  display: block;
  width: 1.5vw;
  height: 1.5vw;
  border-bottom: 5px solid white;
  border-right: 5px solid white;
  transform: rotate(45deg);
  margin: -10px;
  animation: animate 2s infinite;
}

.arrow span:nth-child(2) {
  animation-delay: -0.2s;
}

.arrow span:nth-child(3) {
  animation-delay: -0.4s;
}

@keyframes animate {
  0% {
    opacity: 0;
    transform: rotate(45deg) translate(-20px, -20px);
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: rotate(45deg) translate(20px, 20px);
  }
}
</style>