<template>
    <div>

      <br>
        <v-card-title>
          <v-select
            outlined
            v-model="search"
            flat
            solo-inverted
            hide-details
            :items="InternhsipType"
            prepend-inner-icon="mdi-magnify"
            label="Search By Internship Type"
            ></v-select>
            <v-spacer></v-spacer>
            <v-select
            outlined
            v-model="sortBy"
            flat
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
            Intership Type
          </th>
          <th class="text-left">
            Start Date
          </th>
          <th class="text-left">
            End Date
          </th>
          <th class="text-left">
            Institute Name
          </th>
          <th class="text-left">
            Licence Degree
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
          v-for="i in filteredInternship"
          :key="i.traineeEmail"
        >
          <td>{{ i.intershipType }}</td>
          <td>{{ i.startDate }}</td>

          <td>{{ i.endDate }}</td>
          <td>{{ i.instituteName }}</td>

          <td>{{ i.license }}</td>
          <td>{{ i.status }}</td>

          <td>
            <v-btn
            class="mr-2"
            color="accent"
            @click="response(i.traineeEmail, true)"
            >
                <v-icon       
                >
                    mdi-check
                </v-icon>
                accept
            </v-btn>

            <v-btn
            color="error"
            @click="response(i.traineeEmail, false)"
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
import InternshipApplicationServices from '../services/InternshipApplicationServices';
import InternAccountServices from '../services/InternAccountServices';
export default {
    data () {
        return {
            internship: [],
            status: [
            'UNDEFINED','REFUSED','ACCEPTED'
            ],
            InternhsipType: [
            'INITIATION','PERFECTIONNEMENT','PFE'
            ],
            search: '',
            sortBy: '',
        }
    },

    computed: {
    filteredInternship () {
      return this.internship.filter(intern => {
        return intern.intershipType.startsWith(this.search) && intern.status.startsWith(this.sortBy);
        });
      }
   },


    methods: {
        async get() {
            this.internship = await InternshipApplicationServices.viewAll()

        },
        async response(id,response) {
          if(response == true) {
            await InternAccountServices.acceptInternship(id);
          }
           if(await InternshipApplicationServices.AcceptOrDecline(id,response)) {
               this.get()
           }

        }
    },

    created() {
        this.get();
    }

}
</script>