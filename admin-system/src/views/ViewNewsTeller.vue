<template>
    <div>
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
            class="pt-lg-7"
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
        <div v-if="filteredNewsTellers == ''" justify="center" align="center">
          <v-chip class="ma-2 grey">
            No results matched your search.
          </v-chip>
        </div>
        <v-row
        v-else
        justify="space-around"
        >

    <v-col
    v-for="newsTeller in filteredNewsTellers"
          :key="newsTeller.newsTellerId"
    >
      <v-sheet
        elevation="5"
        rounded="sm"
      >
        <v-sheet
          class="pa-3  d-flex justify-space-between"
          color="accent"
          rounded="sm"
        >

          <v-btn
          @click.stop="dialog = true, setTargetedID(newsTeller.newsTellerId)"
          >
          <v-icon
      >
        mdi-pencil
    </v-icon>
    Edit
    </v-btn>
    <v-dialog
      v-model="dialog"
      max-width="600px"
      v-if="targetedID == newsTeller.newsTellerId"
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
          :label="newsTeller.title"
          required
        ></v-text-field>

        <v-file-input
          v-model="file"
          :rules="[v => !!v || 'File is required']"
          color="deep-purple accent-4"
          counter
          label="File input"
          placeholder="Select your file"
          prepend-icon="mdi-paperclip"
          outlined
          :show-size="1000"
        ></v-file-input>
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
        @click="editItem(newsTeller.newsTellerId)"
        :disabled="!valid"
        >
        Save
      </v-btn>
    </v-card-actions>
      </v-card>
    </v-dialog>



    <v-btn
    @click="deleteItem(newsTeller.newsTellerId)"
    >
      <v-icon
      >
        mdi-delete
      </v-icon>
      Delete
          </v-btn>
        </v-sheet>

          <div class="pa-4"  rounded="sm">
            <v-banner
          icon="mdi-menu-right-outline"
          >{{ newsTeller.title }}</v-banner>
          <v-banner
          icon="mdi-calendar"
          >{{ newsTeller.date }}</v-banner>
          </div>

          <v-sheet
          class="pa-3 text-center"
          rounded="sm"
          justify="center"
          >
            <v-btn
            color="accent"
            @click="download(newsTeller.newsTellerId)"
          >
          <v-icon
      >
        mdi-download
    </v-icon>
    Download Pdf File
    </v-btn>
          </v-sheet>
      
        </v-sheet>
    </v-col>
  </v-row>

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
import NewsTellerServices from '../services/NewsTellerServices'

export default {
    data() {
            return {
              modal: false,
              newsTellers: [],
              search: '',
              date: '',
              snackbar: false,
              text: '',
              valid: false,
              title: '',
              file: '',
              targetedID: null,
              dialog: false,
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
               viewAll () {
                    NewsTellerServices.viewAll().then(response => {
                 this.newsTellers = response
                 })
                .catch(error => {
                 console.log(error)
               })
            },
            async deleteItem(id) {
               if(await NewsTellerServices.delete(id)) {
                this.text = 'Item deleted successfully'
                this.viewAll();
                this.snackbar = true;
               }
            },
            setTargetedID(id) {
              this.targetedID = id;
            },
            async editItem(id) {
              if(this.title != '' && this.file != '') {
              this.valid = true;
              }
              else {
                this.valid = false;
              }
             // console.log(this.valid)
              if(this.valid) {
                this.dialog = false
                let newsTeller = {
                title: this.title,
              }
               let fileString = this.file
               let formData = new FormData()
               formData.append('file', fileString)

                if(await NewsTellerServices.update(id, newsTeller) && await NewsTellerServices.putFile(id, formData)) {
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
            async download(id) {
                await NewsTellerServices.getFile(id).then(response => {
                  const blob = new Blob([response], { type: "application/pdf" });
                    const link = document.createElement("a");
                    link.href = window.URL.createObjectURL(blob);
                    link.download = Math.random().toString(36).slice(2) + ".pdf";
                    link.click();
                })
            },
            reset() {
              this.dialog = false
              this.title = ''
               this.file = ''
            }
            

        },

    created () {
        this.viewAll();
    }
}
</script>