<template>
  <div class="seller-information-window">
    <div></div>
    <div class="button-div">
      <button class="chat-button" >Talk to seller</button>
    </div>
    <h2>Seller information</h2>
    <div class="profile-picture">
      <figure></figure>
    </div>
    <div>
      <label>User name:</label>
      <p></p>
      <label>Email:</label>
      <p></p>
      <label>Phone number:</label>
      <p></p>
      <label>Address:</label>
      <p></p>
      <label>Zip-code:</label>
      <p></p>
    </div>
  </div>
</template>

<script>
export default {
  name: "SellerInformation"
}
</script>

<style scoped>
  .seller-information-window{
    display: grid;
    grid-template-rows: 2fr 1fr 1fr 2fr 4fr;
  }
  .button-div{
    grid-row: 2;
  }

  .chat-button{
    height: inherit;
  }


  .speech-bubble {
    position: relative;
    margin: .5em auto;
    padding: 1em;
    width: 10em; height: 4em;
    border-radius: .25em;
    transform: rotate(-4deg) rotateY(15deg);
    background: #000000;
    font: 2em/4 sans-serif;
    text-align: center;
  }
  .speech-bubble:before, .speech-bubble:after {
    position: absolute;
    z-index: -1;
    content: '';
  }
  .speech-bubble:after {
    top: 0; right: 0; bottom: 0; left: 0;
    border-radius: inherit;
    transform: rotate(2deg) translate(.35em, -.15em) scale(1.02);
    background: #ffd700;
  }
  .speech-bubble:before {
    border: solid 0 transparent;
    border-right: solid 3.5em #ffd700;
    border-bottom: solid .25em #000000;
    bottom: .25em; left: 1.25em;
    width: 0; height: 1em;
    transform: rotate(45deg) skewX(75deg);
  }
</style>