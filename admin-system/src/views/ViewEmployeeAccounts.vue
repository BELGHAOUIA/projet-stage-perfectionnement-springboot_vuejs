<template>
    <div>
        <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
            <h3>Employees' Accounts</h3>
        </v-subheader>
        <v-divider></v-divider>
        <br>
        <v-card-title>
            <v-text-field
            outlined
            v-model="search"
            append-icon="mdi-magnify"
            label="Search By Employee's First Name"
            single-line
            hide-details
            ></v-text-field>
            <v-spacer></v-spacer>
            <v-select
            outlined
            v-model="sortBy"
            solo-inverted
            hide-details
            :items="departments"
            prepend-inner-icon="mdi-magnify"
            label="Search By Departments"
            ></v-select>
        </v-card-title>

        <br>
        <div v-if="filteredEmployees == ''" justify="center" align="center">
          <v-chip class="ma-2 grey">
            No results matched your search.
          </v-chip>
        </div>
        <v-simple-table v-else>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Email
          </th>
          <th class="text-left">
            First Name
          </th>
          <th class="text-left">
            Last Name
          </th>
          <th class="text-left">
            Phone Number
          </th>
          <th class="text-left">
            Department
          </th>
          <th class="text-left">
            Role
          </th>
          <th  class="text-left">
            Actions
        </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="employee in filteredEmployees"
          :key="employee.email"
        >
          <td>{{ employee.email }}</td>
          <td>{{ employee.firstName }}</td>
          <td>{{ employee.lastName }}</td>
          <td>{{ employee.phoneNumber }}</td>
          <td>{{ employee.employeeDepartment }}</td>
          <td>{{ employee.employeeRole }}</td>

          <td class="d-flex">
            <v-icon    
            color="accent"   
                 class="mr-2"
                 @click.stop="dialog = true, setTargetedID(employee.email)"
               >
                 mdi-pencil
               </v-icon>
    <v-dialog
      v-model="dialog"
      max-width="600px"
      v-if="targetedID == employee.email"
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
            v-model="firstName"
            :rules="nameRules"
            counter
            :label="employee.firstName"
            required
          ></v-text-field>

          <v-text-field
            v-model="lastName"
            :rules="nameRules"
            counter
            :label="employee.lastName"
            required
          ></v-text-field>
  
      <v-text-field
          v-model="phoneNumber"
          :counter="8"
          :rules="phoneNumberRules"
          :label="employee.phoneNumber"
          required
        ></v-text-field>

        <v-select
        :rules="[v => !!v || 'Item is required']"
          v-model="department"
          :items="departments"
          prepend-inner-icon="mdi-account"
          menu-props="auto"
          hide-details
          :label="employee.employeeDepartment"
          single-line
          required
        ></v-select>

        <v-text-field
            v-model="role"
            :rules="[v => !!v || 'Role is required']"
            :label="employee.employeeRole"
            required
          ></v-text-field>

        <v-text-field
              v-model="password"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="rulePass"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Password"
              counter
              @click:append="show1 = !show1"
              ></v-text-field>
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
        @click="editItem(employee.email)"
        :disabled="!valid"
        >
        Save
      </v-btn>
    </v-card-actions>
      </v-card>
    </v-dialog>
      
    <v-icon
    color="warning"
        @click="deleteItem(employee.email)"
      >
        mdi-delete
      </v-icon>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>

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
import EmployeeAccountServices from '../services/EmployeeAccountServices'

export default {
    data() {
            return {
                employees: [],
                departments: [
                      'Design Department',
                      'Production Department',
                      'Marketing Department',
                      'Human Resources Department',
                      'Finance Department',
                      'Procurement Department',
                      'Information Technology Department'
                    ],
                search: '',
                sortBy: '',
                valid: false,
                show1: false,
                rulePass: [
          value => !!value || 'Password is required.',
            v => ( v!=null && v.length >= 8) || 'Min 8 characters'
          ],
      nameRules: [
        v => !!v || 'Name is required',
      ],
      phoneNumberRules: [
        v => !!v || 'Phone Number is required',
        v => v.length == 8 || 'Phone Number must be 8 digits',
      ],
      firstName: '',
      lastName: '',
      phoneNumber: '',
      department: '',
      role: '',
      password: '',
      dialog: false,
      targetedID: null,
      snackbar: false,
      text: '',
      
    }
          },

        computed: {
    filteredEmployees () {
      return this.employees.filter(Employee => {
        return Employee.firstName.toLowerCase().startsWith(this.search) && Employee.employeeDepartment.startsWith(this.sortBy);
        });
      }
   },
        methods : {
                viewAll () {
                     EmployeeAccountServices.viewAll().then(response => {
                 this.employees = response
                 })
                .catch(error => {
                 console.log(error)
               })
            },
            async deleteItem(id) {
              if(await EmployeeAccountServices.delete(id)){
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
                 this.firstName != '' &&
                  this.lastName != '' &&
                   this.phoneNumber != '' &&
                  this.department != '' && 
                   this.role != '' &&
                   this.password != '' 
              ) {
              this.valid = true;
              }
              else {
                this.valid = false;
              }
              if(this.valid) {
                const employee = {
                  firstName : this.firstName,
                  lastName: this.lastName,
                  phoneNumber: this.phoneNumber,
                  employeeDepartment: this.department,
                  employeeRole: this.role,
                  password: this.password
                }
                if(await EmployeeAccountServices.update(id, employee)) {
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
              this.firstName = ''
                    this.lastName = ''
                     this.phoneNumber = ''
                    this.department = ''
                     this.role = ''
                     this.password = ''
            }

        },
    created () {
        this.viewAll();
    }
}
</script>