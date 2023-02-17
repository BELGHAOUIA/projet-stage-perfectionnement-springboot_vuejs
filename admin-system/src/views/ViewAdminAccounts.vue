<template>
    <div class="ViewAdminAccounts">
        <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
            <h3>Admins' Accounts</h3>
        </v-subheader>
        <v-divider></v-divider>
        <br>

        <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Admin Account Role
          </th>
          <th class="text-left">
            Admin Account User Name
          </th>
          <th  class="text-left">
            Actions
        </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="admin in admins"
          :key="admin.adminRole"
        >
          <td>{{ admin.adminRole }}</td>
          <td>{{ admin.adminUserName }}</td>
          <td>
      <v-icon
        
        class="mr-2"
        @click="editItem(admin.adminRole)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        
        @click="deleteItem(admin.adminRole)"
      >
        mdi-delete
      </v-icon>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
    </div>
</template>

<script>
import EmployeeAccountServices from '../services/EmployeeAccountServices'

export default {
    name: "ViewAdminAccounts",
    data() {
            return {
                admins: [],
            }
        },

        methods : {
            async viewAdminsAccounts () {
                 await EmployeeAccountServices.viewAdminsAccounts().then(response => {
                 this.admins = response
                 })
                .catch(error => {
                 console.log(error)
               })
            },
            deleteItem(id) {
              EmployeeAccountServices.deleteAdminAccount(id);
                this.admins = null;
                this.$emit('adminAccount');
            }

        },
        watch: {
        admins: {
        immediate: true,
         handler: 'viewAdminsAccounts'
    }
  },
    created () {
        this.viewAdminsAccounts();
    }

}
</script>