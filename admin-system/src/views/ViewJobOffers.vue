<template>
    <div>
        <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
            <h3>Job Offers List</h3>
        </v-subheader>
        <v-divider></v-divider>
        <br>
        <v-card-title>
            <v-text-field
            outlined
            v-model="search"
            append-icon="mdi-magnify"
            label="Search By Job Offer's Title"
            single-line
            hide-details
            ></v-text-field>
            <v-spacer></v-spacer>
            <v-select
            v-model="sortBy"
            outlined
            solo-inverted
            hide-details
            :items="departments"
            prepend-inner-icon="mdi-magnify"
            label="Search By Departments"
            ></v-select>
            <v-spacer></v-spacer>
      <v-dialog
        ref="dialog"
        v-model="modal"
        :return-value.sync="date"
        persistent
        width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
          outlined
          class="pt-lg-8"
            v-model="date"
            label="Pick A Date"
            prepend-inner-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          v-model="date"
          scrollable
          color="primary"
        >
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            @click="modal = false"
          >
            Cancel
          </v-btn>
          <v-btn
            text
            color="primary"
            @click="$refs.dialog.save(date)"
          >
            OK
          </v-btn>
        </v-date-picker>
      </v-dialog>
        </v-card-title>
        <br>
        <div v-if="filteredJobOffers == ''" justify="center" align="center">
          <v-chip class="ma-2 grey">
            No results matched your search.
          </v-chip>
        </div>
    <template v-else>
        <v-timeline
      :dense="$vuetify.breakpoint.smAndDown"
    >
      <v-timeline-item
        v-for="jobOffer in filteredJobOffers"
        :key="jobOffer.jobOfferId"
      >
          <span slot="opposite">Date: {{jobOffer.date}}</span>
          <v-card class="elevation-2">
              <v-card-title class="text-h5">
                  {{jobOffer.title}}
                </v-card-title>
                <v-card-subtitle class="text-decoration-underline">
                   {{jobOffer.department}}
                </v-card-subtitle>
                <v-card-text>
                    
                    <v-expansion-panels>
                        <v-expansion-panel
                        >
                        <v-expansion-panel-header>
                            Description 
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                            {{jobOffer.description}} 
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions class="justify-space-around">
                <v-icon
                
                class="mr-2"
                @click.stop="dialog = true, setTargetedID(jobOffer.jobOfferId)"                >
                mdi-pencil
            </v-icon>

            <v-dialog
      v-model="dialog"
      max-width="600px"
      v-if="targetedID == jobOffer.jobOfferId"
    >
      <v-card
      class="pa-5"
>
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
          :label="jobOffer.title"
          required
        ></v-text-field>
 
        <v-select
        :rules="[v => !!v || 'Item is required']"
          v-model="department"
          :items="departments"
          prepend-inner-icon="mdi-account"
          menu-props="auto"
          hide-details
          :label="jobOffer.department"
          single-line
          required
        ></v-select>
 
        <v-textarea
        :rules="[v => !!v || 'Description is required']"
        v-model="description"
        counter
          prepend-inner-icon="mdi-comment"
          :label="jobOffer.description"
          rows="2"
          required
        ></v-textarea>
        </v-form>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
          color="blue darken-1"
          text
          @click="reset()"
          >
          Close
        </v-btn>
        <v-btn
        color="blue darken-1"
        text
        @click="editItem(jobOffer.jobOfferId)"
        :disabled="!valid"
        >
        Save
      </v-btn>
    </v-card-actions>
      </v-card>
    </v-dialog>


            <v-icon
            
            @click="deleteItem(jobOffer.jobOfferId)"
            >
            mdi-delete
        </v-icon>    
    </v-card-actions>   
</v-card>
</v-timeline-item>
</v-timeline>
</template>
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
import JobOfferServices from '../services/JobOfferServices'

export default {
    data() {
            return {
                    snackbar: false,
                    text: '',
                    valid: false,
                    dialog: false,
                    modal: false,
                    jobOffers: [],
                    departments: [
                      'Design Department',
                      'Production Department',
                      'Marketing Department',
                      'Human Resources Department',
                      'Finance Department',
                      'Procurement Department',
                      'Information Technology Department'
                    ],
                    sortBy: '',
                    search: '',
                    date: '',
                    targetedID: '',
                    title: '',
                    description: '',
                    department: '',
                  }
                },


        computed: {
    filteredJobOffers() {
      return this.jobOffers.filter(jobOffer => {
        return (jobOffer.title.toLowerCase().startsWith(this.search)) && (jobOffer.department.startsWith(this.sortBy)) && jobOffer.date.startsWith(this.date);
        });
      }
   },
        methods : {
              viewAll () {
                   JobOfferServices.viewAll().then(response => {
                 this.jobOffers = response
                 })
                .catch(error => {
                 console.log(error)
               })
            },
            async deleteItem(id) {
                if(await JobOfferServices.delete(id)){
                  this.text = 'Item deleted successfully'
                this.viewAll();
                this.snackbar = true
                }
            },
            setTargetedID(id) {
              this.targetedID = id;
            }
            ,
            async editItem(id) {
              if(
                 this.title != '' &&
                  this.description != '' &&
                  this.department != ''
              ) {
              this.valid = true;
              }
              else {
                this.valid = false;
              }
              if(this.valid) {
                const jobOffer = {
                  title : this.title,
                  description: this.description,
                  department: this.department,
                }
                if(await JobOfferServices.update(id, jobOffer)) {
                  this.viewAll();
                  this.text = 'Item edited successfully'
                  this.reset()
                }
              }
              else {
                this.text = 'Please fill the form correctly'
              }
              this.snackbar = true
            },
            reset () {
              this.dialog = false
                    this.department = ''
                     this.title = ''
                     this.description = ''
            }

        },

    created () {
        this.viewAll();
    }
}
</script>