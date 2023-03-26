<template>
    <div>
      <v-subheader>
          <h3>Internhsip Application Form</h3>
        </v-subheader>
      <v-divider></v-divider>
    <br>
    <br>
        <v-form 
      class="form text-center mr-16 ml-16"
      ref="form"
      v-model="valid"
      lazy-validation
      @submit.prevent="validate"
    >
  
         <v-text-field
         outlined
          prepend-icon="mdi-school"
            v-model="instituteName"
            :rules="[v => !!v || 'Item is required']"
            counter
            label="Institute Name"
            required
          ></v-text-field>

         <v-text-field
         outlined
          prepend-icon="mdi-stamper"
            v-model="license"
            :rules="[v => !!v || 'Item is required']"
            counter
            label="Licence"
            required
          ></v-text-field>

            <v-select
            outlined
          prepend-icon="mdi-palette-swatch"
                :rules="[v => !!v || 'Item is required']"
                v-model="intershipType"
                :items="intershipTypes"
                menu-props="auto"
                hide-details
                label="Select Internship Type"
                single-line
                required
                ></v-select>
<br>
                <v-dialog
            ref="dialog1"
            v-model="modal1"
            :return-value.sync="date"
            persistent
            width="290px"
          >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
          outlined
          prepend-icon="mdi-calendar"
          :rules="[v => !!v || 'Item is required']"
            v-model="startDate"
            label="Pick Start Date"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          v-model="startDate"
          scrollable
        >
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            @click="modal1 = false"
            tile
          >
            Cancel
          </v-btn>
          <v-btn
          tile
            text
            color="primary"
            @click="$refs.dialog1.save(startDate)"
          >
            OK
            </v-btn>
          </v-date-picker>
        </v-dialog>

        <v-dialog
            ref="dialog"
            v-model="modal2"
            :return-value.sync="date"
            persistent
            width="290px"
          >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
          outlined
          prepend-icon="mdi-calendar"
          :rules="[v => !!v || 'Item is required']"
            v-model="endDate"
            label="Pick End Date"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          v-model="endDate"
          scrollable
        >
          <v-spacer></v-spacer>
          <v-btn
          tile
            text
            color="primary"
            @click="modal2 = false"
          >
            Cancel
          </v-btn>
          <v-btn
            text
            tile
            color="primary"
            @click="$refs.dialog.save(endDate)"
          >
            OK
            </v-btn>
          </v-date-picker>
        </v-dialog>

      <v-btn
      tile
        :disabled="!valid"
        color="accent"
        class="mr-4"
        @click="validate"
        x-large
      >
        Validate
      </v-btn>
  
      <v-btn
      tile
        color="warning"
        class="mr-4"
        @click="reset"
        x-large
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
            :color="color"
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
import InternshipApplicationServices from '../services/InternshipApplicationServices';
export default {
    data() {
        return {
            snackbar: false,
            valid: false,
            modal1: false,
            modal2: false,
            intershipTypes: [
                'INITIATION', 'PERFECTIONNEMENT', 'PFE'
            ],
            traineeEmail: '',
            intershipType: '',
            startDate: '',
            endDate: '',
            instituteName: '',
            license: '',
            color: ''
        }
    },
    methods: {
        async validate() {
            if(this.$session.exists() && this.$session.get("key").accountType == "Intern" ) {
            this.valid = this.$refs.form.validate();
          if(!this.valid) {
            this.text = 'Please fill the form correctly'
            this.color= 'red'
          }
          else { 
            const internship = {
                traineeEmail: this.$session.get('key').id,
                intershipType: this.intershipType,
                startDate: this.startDate,
                endDate: this.endDate,
                instituteName: this.instituteName,
                license: this.license
            }

            if (await InternshipApplicationServices.add(internship)) {
            this.text =' Application Submitted Successfuly';
            this.color= 'green'
          }
        }
    }
    else {
        this.text = 'Error, Invalid Account'
                this.color = 'red'
    }
        this.snackbar = true;
      this.reset();
    },
    reset () {
          this.$refs.form.reset()
        },
    }
}
</script>


