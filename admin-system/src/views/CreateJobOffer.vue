<template>
    <div>
      <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
              <h3>Creating A Job Offer</h3>
          </v-subheader>
          <v-divider></v-divider>
          <br>
          <br>
    <v-form 
      class="form text-center mr-16 ml-16"
      ref="form"
      v-model="valid"
      lazy-validation

    >
 
        <v-text-field
        :rules="[v => !!v || 'Title is required']"
          v-model="title"
          counter
          label="Enter The Title"
          required
          prepend-icon="mdi-star"
          outlined
        ></v-text-field>
 
        <v-select
        :rules="[v => !!v || 'Item is required']"
          v-model="department"
          :items="departments"
          prepend-icon="mdi-account-group"
          menu-props="auto"
          hide-details
          label="Select Department"
          single-line
          required
          outlined
        ></v-select>
 <br>
        <v-textarea
        :rules="[v => !!v || 'Description is required']"
        v-model="description"
        counter
        prepend-icon="mdi-message"
          label="Type The Description"
          rows="2"
          required
          outlined
        ></v-textarea>
      
      <v-btn
        :disabled="!valid"
        color="accent"
        class="mr-4"
        @click="validate"
      >
        Validate
      </v-btn>
  
      <v-btn
        color="warning"
        class="mr-4"
        @click="reset"
      >
        Reset Form
      </v-btn>
  
    </v-form>
    <v-snackbar
        v-model="snackbar"
      >
        {{ text }}
  
        <template v-slot:action="{ attrs }">
          <v-btn
            color="green"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
  </div>
  </template>
  
  <script>
  import JobOfferServices from '../services/JobOfferServices';
  
  export default {
    name: 'CreateEmployeeAccount',
    data: () => ({
        snackbar: false,
        text: '',
        valid: false,
        departments: [
                      'Design Department',
                      'Production Department',
                      'Marketing Department',
                      'Human Resources Department',
                      'Finance Department',
                      'Procurement Department',
                      'Information Technology Department'
                    ],
        title: '',
        description: '',
        department: '',
      }),
  
      methods: {
  
       async validate () {
          this.valid = this.$refs.form.validate();
          if(!this.valid) {
            this.text = 'Please fill the form correctly'
          }
          else {
          const jobOffer = {
            title: this.title,
            description: this.description,
            department: this.department,
          }
          JobOfferServices.add(jobOffer);
          this.text = 'Job Offer Created Successfuly';
        }

        this.snackbar = true;
        this.reset();
        },

        reset () {
          this.$refs.form.reset()
        },
      },
      watch() {
      },
      created () {
      }
  }
  </script>
  