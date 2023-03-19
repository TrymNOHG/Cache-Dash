<template>
  <div>
    <h1>Create an event</h1>
    <form @submit.prevent="sendForm">
      <BasicSelect
          :options="categories"
          v-model="event.category"
          label="Select a category"
      />

      <fieldset>
        <legend>Name & describe your event</legend>

        <BasicInput
            v-model="event.title"
            label="Title"
            type="text"
            error="This input has an error!"
        />

        <BasicInput
            v-model="event.description"
            label="Description"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Where is your event?</legend>

        <BasicInput
            v-model="event.location"
            label="Location"
            type="text"
        />
      </fieldset>

      <fieldset>
        <legend>Pets</legend>

        <p>Are pets allowed?</p>
        <div>
          <BasicRadioGroup
              v-model="event.pets"
              name="pets"
              :options="petOptions"
          />
        </div>
      </fieldset>

      <fieldset>
        <legend>Extras</legend>
        <div>
          <BasicCheckbox
              v-model="event.extras.catering"
              label="Catering"
          />
        </div>

        <div>
          <BasicCheckbox
              v-model="event.extras.music"
              label="Live music"
          />
        </div>
      </fieldset>

      <button type="submit">Submit</button>
    </form>

    <pre>{{ event }}</pre>
  </div>
</template>

<script>
import axios from 'axios'
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import BasicSelect from "@/components/basicInputComponents/BasicSelect.vue";
import BasicRadioGroup from "@/components/basicInputComponents/BasicRadioGroup.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";

export default {
  components: {BasicCheckbox, BasicRadioGroup, BasicInput, BasicSelect},
  data () {
    return {
      categories: [
        'sustainability',
        'nature',
        'animal welfare',
        'housing',
        'education',
        'food',
        'community'
      ],
      event: {
        category: '',
        title: '',
        description: '',
        location: '',
        pets: 1,
        extras: {
          catering: false,
          music: false
        }
      },
      petOptions: [
        { label: 'Yes', value: 1 },
        { label: 'No', value: 0 }
      ]
    }
  },
  methods: {
    sendForm (e) {
      axios.post('https://my-json-server.typicode.com/Code-Pop/Vue-3-Forms/events', this.event)
          .then(function (response) {
            console.log('Response', response)
          })
          .catch(function (err) {
            console.log('Error', err)
          })
    }
  }
}
</script>

<style>
fieldset {
  border: 0;
  margin: 0;
  padding: 0;
}

legend {
  font-size: 28px;
  font-weight: 700;
  margin-top: 20px;
}
</style>