<template>
    <div>
        <div v-if="internship == null && file == null">
            <v-img  src="./../assets/images/Artboard1.png"></v-img>
        </div>
        <div v-else>
            <div class="d-flex justify-space-around">
          <v-chip class="ma-2 grey" v-if="internship.status=='UNDEFINED'" > 
            {{ internship.status }} 
          </v-chip>
                <v-chip class="ma-2" v-else :class="[internship.status=='ACCEPTED' ? 'green' : 'red']">{{ internship.status }} </v-chip>
                <v-spacer></v-spacer>
                <v-btn
                v-if="internship.status == 'ACCEPTED'"
                @click="download()"
                >
                <v-icon
                >
                    mdi-download
                </v-icon>
                Download Pdf File
                </v-btn>
            </div>

            <v-divider></v-divider>
                <pdf class="pl-md-16 pr-md-16 ma-lg-16" v-if="internship.status == 'ACCEPTED'" :src="file"></pdf>
        </div>
    </div>
</template>


<script>
import InternshipApplicationServices from '../services/InternshipApplicationServices';
import InternAccountServices from '../services/InternAccountServices';
import pdf from 'vue-pdf'

export default {
    components: {
    pdf
  },
    data () {
        return {
            internship: [],
            file: ''
        }
    },

    methods: {
        async get() {
            if(!this.$session.exists() || this.$session.get('key').accountType != 'Intern') {
                this.internship = null
                this.file = null
            }
            else {
                this.internship = await InternshipApplicationServices.getApplication(this.$session.get('key').id);
                //console.log(this.internship)
                if(this.internship != null) {
                    if(this.internship.status == 'ACCEPTED') {
                        this.file = await InternAccountServices.getFile(this.$session.get('key').id)
                        .then(response => {
                            const blob = new Blob([response]);
                            const objectUrl = URL.createObjectURL(blob);
                            this.file = objectUrl;                        
                            objectUrl.revokeObjectURL
                        })
                    }
                }
                else {
                    this.internship = null
                    this.file = null
                }
                
            }
        },

        async download() {
            if(this.internship.status == 'ACCEPTED') {
                 await InternAccountServices.download(this.$session.get('key').id)
                .then(response=> {
                    const blob = new Blob([response], { type: "application/pdf" });
                    const link = document.createElement("a");
                    link.href = window.URL.createObjectURL(blob);
                    link.download = Math.random().toString(36).slice(2) + ".pdf";
                    link.click();
                    })
                    .catch(error => {
                        console.log(error);
                    });
                }
        }
    },

    created() {
        this.get();
    }

}
</script>