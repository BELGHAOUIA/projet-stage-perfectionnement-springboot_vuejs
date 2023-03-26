<template>
    <div>
        <v-subheader class="py-0 justify-space-between rounded-lg">
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
            outlined
            v-model="sortBy"
            flat
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
          class="pt-lg-8"
          outlined
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
                <v-btn
                class="mr-2"
                @click="applyForJob(jobOffer.jobOfferId)"
                >
                <v-icon>mdi-check</v-icon>
                Apply
            </v-btn> 
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
import JobOfferApplicationServices from '../services/JobOfferApplicationServices'
import JobOfferServices from '../services/JobOfferServices'

export default {
    data() {
            return {
                    snackbar: false,
                    text: '',
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
            async applyForJob(jobId) {
              if(this.$session.exists() && this.$session.get("key").accountType == "Job Seeker" ) {
              let accountId = this.$session.get("key").id;
                const jobApplication = {
                  jobSeekerOpenJobId: {
                    jobSeekerId: accountId,
                    jobOfferId: jobId
                  }
                }
                if(await JobOfferApplicationServices.add(jobApplication)) {
                this.text = 'Applied for job successfully'
                this.snackbar = true
                }
              }
              else {
                this.text = 'Error, Invalid Account'
                this.snackbar = true
              }
            },
        },

    created () {
        this.viewAll();
    }
}
</script>