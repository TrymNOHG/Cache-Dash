<template>
  <div class="filter-window">
    <h2> placeholder{{ categoryName }} </h2>
    <div class="category-filter">
      <ul>
        <BasicCheckbox v-for="category in categories">
          label: {{ category.name }}
        </BasicCheckbox>

      </ul>
    </div>
    <div class="searchArea">
      <basic-input
          id="searchArea"
          type="search"
          label="Search area"
      ></basic-input>
    </div>
    <label>Location</label>
    <BasicSelect
        options="counties"
      />
  </div>
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";
import BasicSelect from "@/components/basicInputComponents/BasicSelect.vue";

export default {
  name: "filterComponent",
  components: {BasicSelect, BasicCheckbox, BasicInput},
  props: {
    category:{},
    categories:[],
    counties:[

    ]
  }
}
</script>

<style scoped>
  .filter-window{
    background-color: #7EB09B;
    opacity: 70%;
  }
  .searchArea{
    padding: 10px

  }
</style>