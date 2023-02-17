<template>
  <div>
    <br>
        <v-card-title>
            <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search By Candidate's Email"
            single-line
            hide-details
            outlined
            ></v-text-field>
            <v-spacer></v-spacer>
            <v-select
            outlined
            v-model="sortBy"
            solo-inverted
            hide-details
            :items="status"
            prepend-inner-icon="mdi-magnify"
            label="Search By Status"
            ></v-select>
        </v-card-title>

        <br>

      <v-simple-table>
  <template v-slot:default>
    <thead>
      <tr>
        <th class="text-left">
          Candidate's Email
        </th>
        <th class="text-left">
          Candidate's CV
        </th>
        <th class="text-left">
          Application Status
        </th>
        <th class="text-left">
          Actions
        </th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="i in filteredJobs"
        :key="i.jobSeekerOpenJobId"
      >
      <td>
        {{ i.jobSeekerOpenJobId.jobSeekerId }}
      </td>
        <td>
          <v-btn
          color="info"
          @click="download(i.jobSeekerOpenJobId.jobSeekerId)"
          >
          <v-icon
          >
            mdi-download
        </v-icon>
        Download CV
        </v-btn>  
        </td>
        
        <td>{{ i.status }}</td>
        <td>
          <v-btn
          class="mr-2"
          color="accent"
          @click="response(i.jobSeekerOpenJobId, true)"
          >
              <v-icon       
              >
                  mdi-check
              </v-icon>
              accept
          </v-btn>

          <v-btn
          color="error"
          @click="response(i.jobSeekerOpenJobId, false)"
          >
              <v-icon
               >
                   mdi-close
               </v-icon>
               decline
          </v-btn>
        </td>
      </tr>
      </tbody>
      </template>
      </v-simple-table>
  </div>
</template>


<script>
import JobOfferApplicationServices from '../services/JobOfferApplicationServices';
//import JobOfferServices from '../services/JobOfferServices';
import JobSeekerAccountServices from '../services/JobSeekerAccountServices';
export default {
  data () {
      return {
          jobs: [],
          status: [
          'UNDEFINED','REFUSED','ACCEPTED'
          ],
          search: '',
          sortBy: '',
      }
  },

  computed: {
    filteredJobs () {
      return this.jobs.filter(job => {
        return job.jobSeekerOpenJobId.jobSeekerId.toLowerCase().startsWith(this.search) && job.status.startsWith(this.sortBy);
        });
      }
   },

  methods: {
      async get() {
          this.jobs = await JobOfferApplicationServices.viewAll()
          },
      async response(id,response) {
         if(await JobOfferApplicationServices.AcceptOrDecline(id,response)) {
             this.get()
         }
      },
      async download(id) {
                await JobSeekerAccountServices.getFile(id).then(response => {
                  const blob = new Blob([response], { type: "application/pdf" });
                    const link = document.createElement("a");
                    link.href = window.URL.createObjectURL(blob);
                    link.download = Math.random().toString(36).slice(2) + ".pdf";
                    link.click();
                })
            },
  },

  created() {
      this.get();
  }

}
</script>