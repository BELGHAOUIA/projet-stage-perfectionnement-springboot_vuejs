<template>
    <div>
      <div v-if="openJobs == null">
        <v-img  src="./../assets/images/Artboard1.png"></v-img>
      </div>

      <div v-else>
        <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Job Title
          </th>
          <th class="text-left">
            Application Status
          </th>
          <th class="text-left">
            Message
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="openJob in openJobs"
          :key="openJob.jobSeekerId"
        >
          <td>{{ openJob.jobSeekerOpenJobId.jobSeekerId }}</td>
          <td>
            <v-chip class="ma-2 grey" v-if="openJob.status=='UNDEFINED'" > 
            {{ openJob.status }} 
          </v-chip>
                <v-chip class="ma-2" v-else :class="[openJob.status=='ACCEPTED' ? 'green' : 'red']">{{ openJob.status }} </v-chip>  
          </td>
          <td v-if="openJob.status == 'ACCEPTED'">Congratulation, you will be expecting an Email from us.</td>
          <td v-if="openJob.status == 'REFUSED'">Thank you for your interest, better luck next time.</td>
          <td v-if="openJob.status == 'UNDEFINED'">Unread.</td>
        </tr>
        
        </tbody>
        </template>
        </v-simple-table>
    </div>
  </div>
</template>


<script>
import JobOfferApplicationServices from '../services/JobOfferApplicationServices';
import JobOfferServices from '../services/JobOfferServices';
export default {
    data () {
        return {
            openJobs: [],
        }
    },

    methods: {
        async get() {
          if(this.$session.exists() && this.$session.get('key').accountType == 'Job Seeker') {
            this.openJobs = await JobOfferApplicationServices.getApplication(this.$session.get('key').id);
            if(this.openJobs.length) {
            for(let i = 0; i < this.openJobs.length; i++) {
              //console.log(this.openJobs.length)
              const title = await JobOfferServices.getById(this.openJobs[i].jobSeekerOpenJobId.jobOfferId)
              this.openJobs[i].jobSeekerOpenJobId.jobSeekerId = title.title;
            }
          }
          else {
            this.openJobs = null
          }
          }
          else {
            this.openJobs = null
          }
        }
    },

    created() {
        this.get();
    }

}
</script>