<template>
    <div>
        <v-subheader class="py-0 ma-0">
            <h3>Statistics</h3>
        </v-subheader>
        <v-divider></v-divider>
                <div class="d-lg-flex">

                     <chart :options="jobApplication" :loading="loading" auto-resize></chart>
                     <chart :options="internshipApplication" :loading="loading"></chart>
                
                </div>
                     <v-row>
                    <v-col>
                        <v-card elevation="2" class="rounded-lg" style="border-bottom: 10px solid #7E57C2;">
                            <v-card-text class="d-inline-flex justify-space-between">
                                <div>
                                    <strong>ADMIN ACCOUNTS TOTAL COUNT</strong>
                                </div>
                                <v-icon color="#7E57C2" large>mdi-account</v-icon>
                                <v-avatar size="80" style="border: 3px solid #7E57C2">
                                    <h1>{{ adminCount }}</h1>
                                </v-avatar>
                            </v-card-text>
                        </v-card>
                    </v-col>

                    <v-col>
                        <v-card elevation="2" class="rounded-lg" style="border-bottom: 10px solid #5C6BC0;">
                            <v-card-text class="d-inline-flex justify-space-between">
                                <div>
                                    <strong>EMPLOYEES ACCOUNTS TOTAL COUNT</strong>
                                </div>
                                <v-icon color="#5C6BC0" large>mdi-account</v-icon>
                                <v-avatar size="80" style="border: 3px solid #5C6BC0">
                                    <h1>{{ employeeCount }}</h1>
                                </v-avatar>
                            </v-card-text>
                        </v-card>

                    </v-col>
        </v-row>

        <v-row>
                    <v-col>
                        <v-card elevation="2" class="rounded-lg" style="border-bottom: 10px solid #42A5F5;">
                            <v-card-text class="d-inline-flex justify-space-between">
                                <div>
                                    <strong>INTERNS ACCOUNTS TOTAL COUNT</strong>
                                </div>
                                <v-icon color="#42A5F5" large>mdi-account</v-icon>
                                <v-avatar size="80" style="border: 3px solid #42A5F5">
                                    <h1>{{ internCount }}</h1>
                                </v-avatar>
                            </v-card-text>
                        </v-card>
                    </v-col>

                    <v-col>
                        <v-card elevation="2" class="rounded-lg" style="border-bottom: 10px solid #26C6DA;">
                            <v-card-text class="d-inline-flex justify-space-between">
                                <div>
                                    <strong>JOB SEEKERS ACCOUNTS TOTAL COUNT</strong>
                                </div>
                                <v-icon color="#26C6DA" large>mdi-account</v-icon>
                                <v-avatar size="80" style="border: 3px solid #26C6DA">
                                    <h1>{{ jobSeekerCount }}</h1>
                                </v-avatar>
                            </v-card-text>
                        </v-card>
                    </v-col>
        </v-row>

    
    </div>
</template>

<script>
import AdminAccountServices from '../services/AdminAccountServices';
import EmployeeAccountServices from '../services/EmployeeAccountServices';
import InternAccountServices from '../services/InternAccountServices';
import JobSeekerAccountServices from '../services/JobSeekerAccountServices';

import InternshipApplicationServices from '../services/InternshipApplicationServices';
import JobApplicationServices from '../services/JobOfferApplicationServices';

import 'echarts/lib/component/title';


    export default {
        name: "Dashboard",
        data() {
            return {
                adminCount: null,
                employeeCount: null,
                internCount: null,
                jobSeekerCount: null,

                jobApplication: {
                                    xAxis: {
                                        data: ['Undefined', 'Accepted', 'Refused']
                                    },
                                    yAxis: {
                                        type: 'value'
                                    },
                                    tooltip: {},
                                    series: [
                                        {
                                        name: 'Job Applications',
                                        type: 'bar',
                                        data: []
                                        }
                                    ],
                                    title: {
                                        text: 'Job Applications Statistics',
                                        x: 'center',
                                        textStyle: {
                                        fontSize: 24
                                        }
                                    },
                                    color: ['#26C6DA']
                                },
                                internshipApplication: {
                                    xAxis: {
                                        data: ['Undefined', 'Accepted', 'Refused']
                                    },
                                    yAxis: {
                                        type: 'value'
                                    },
                                    tooltip: {},
                                    series: [
                                        {
                                        name: 'internship Application',
                                        type: 'bar',
                                        data: []
                                        }
                                    ],
                                    title: {
                                        text: 'internship Applications Statistics',
                                        x: 'center',
                                        textStyle: {
                                        fontSize: 24
                                        }
                                    },
                                    color: ['#42A5F5']
                                }
            }
        },

        methods: {
             async AccountCount () {
                this.adminCount = await AdminAccountServices.count();
                this.employeeCount = await EmployeeAccountServices.count();

                this.internCount = await InternAccountServices.count();
                this.jobSeekerCount = await JobSeekerAccountServices.count();

                let notDefined = await InternshipApplicationServices.undefined();
                let accepted = await InternshipApplicationServices.accepted();
                let refused = await InternshipApplicationServices.refused();

                this.internshipApplication.series[0].data = [notDefined,accepted,refused]
               // console.log(this.internshipApplication.series.data)

                 notDefined = await JobApplicationServices.undefined();
                 accepted = await JobApplicationServices.accepted();
                 refused = await JobApplicationServices.refused();

                this.jobApplication.series[0].data = [notDefined,accepted,refused]
               // console.log(this.internshipApplication.series.data)

            }
        },

        created() {
            this.AccountCount();
        }
      
    }
</script>

<style scoped>

/*.chart-wrapper {
  width: 100%;
  height: 600px;
}.echarts {
  width: 100%;
  height: 100%;
}*/
</style>