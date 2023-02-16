<template>
    <div>
      <div v-if="accountType == false">
       <v-img  src="./../assets/images/Artboard1.png"></v-img>
      </div>
      <div v-else>
        <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
          <h3>News Tellers List</h3>
        </v-subheader>
        <v-divider></v-divider>
        <br>
        <v-card-title>
          <v-text-field
          outlined
          v-model="search"
          append-icon="mdi-magnify"
          label="Search By News Teller's Title"
          single-line
          hide-details
          ></v-text-field>
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
          color="teal"
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

<v-card
v-for="newsTeller in filteredNewsTellers"
:key="newsTeller.newsTellerId"
class="mb-5"
>

<div class="pa-4"  rounded="sm">
  <v-banner
  icon="mdi-calendar"
  >{{ newsTeller.date }}</v-banner>
  <v-banner
  icon="mdi-menu-right-outline"
  >
  <v-expansion-panels style="border: 2px solid black" >
    <v-expansion-panel>
      <v-expansion-panel-header>
        {{ newsTeller.title }}
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <pdf :src="newsTeller.newsPdfFile"></pdf>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</v-banner>
</div>

</v-card>
</div>
</div>
</template>

<script>
import NewsTellerServices from '../services/NewsTellerServices'
import pdf from 'vue-pdf'

export default {
    components: {
    pdf
  },
    data() {
            return {
              modal: false,
              newsTellers: [],
              search: '',
              date: '',
              snackbar: false,
              accountType: false
            }
        },
        computed: {
    filteredNewsTellers () {
      return this.newsTellers.filter(newsTeller => {
          return newsTeller.title.toLowerCase().startsWith(this.search) && newsTeller.date.startsWith(this.date);
          
        });
      }
   },
        methods : {
               async viewAll () {
                 await NewsTellerServices.viewAll().then((response) =>{
                    this.newsTellers = response
                 })

                for(let i = 0; i < this.newsTellers.length; i++) {
                await NewsTellerServices.getFile(this.newsTellers[i].newsTellerId)
                .then(response => {
                    const blob = new Blob([response]);
                    const objectUrl = URL.createObjectURL(blob);
                    this.newsTellers[i].newsPdfFile = objectUrl;
                    objectUrl.revokeObjectURL
                })
                 }
            },
            connected() {
                if (this.$session.exists() && this.$session.get("key").accountType == 'Employee') {
                   this.accountType = true
                }
            }
        },
  
    created () {
        this.viewAll();
    },
    mounted () {
     this.connected();
    }
}
</script>