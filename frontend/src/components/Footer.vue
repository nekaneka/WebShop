<template>
  <footer class="bg-dark text-white pt-5 pb-4">
    <div class="containter text-center text-md-left">
      <div class="row text-center text-md-left">
        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
          <h5
            class="text-uppercase mb-4 font-weight-bold"
            style="color: #339bff; font-weight: bold"
          >
            IMSeShop.at
          </h5>
          <p>Milestone 2: Implementation of IS based on relational DBMS.</p>
          <p>2.4. Implementation Web system relational DBMS</p>
        </div>

        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
          <h5
            class="text-uppercase mb-4"
            style="color: #339bff; font-weight: bold"
          >
            technologies
          </h5>
          <p>Bootstrap</p>
          <p>Css</p>
          <p>JavaScript</p>
        </div>

        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-2">
          <h5
            class="text-uppercase mb-4"
            style="color: #339bff; font-weight: bold"
          >
            Pages
          </h5>
          <p>
            <router-link
              v-if="employeeLoggedIn"
              class="nav-link active"
              to="/account"
              >Dashboard</router-link
            >
          </p>
          <p>
            <router-link
              v-if="!userLoggedIn && !employeeLoggedIn"
              class="nav-link active"
              to="/login"
              >Login</router-link
            >
          </p>
          <p>
            <router-link v-if="userLoggedIn" class="nav-link active" to="/cart"
              >Cart</router-link
            >
          </p>

          <p>
            <router-link v-if="userLoggedIn" class="nav-link active" to="/user_account"
              >Account</router-link
            >
          </p>
        </div>

        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
          <h5
            class="text-uppercase mb-2"
            style="color: #339bff; font-weight: bold"
          >
            Contact
          </h5>
          <p><i class="fas fa-envelope"></i> a01576891@unet.univie.ac.at</p>
          <p><i class="fas fa-envelope"></i> a01650669@unet.univie.ac.at</p>
        </div>
      </div>

      <hr class="mb-4" />
      <div class="row align-items-center">
        <div class="col-md-7 col-lg-12">
          Copyright ©2021 All rights reserved | Gruppe 10
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  data() {
    return {
      userLoggedIn: false,
      employeeLoggedIn: false,
    };
  },

  methods: {
    isUserLogedIn() {
      AuthService.isUserLogedIn()
        .then((response) => (this.userLoggedIn = response.data))
        .catch((error) => {
          console.log(error);
        });
    },

    isEmployeeLogedIn() {
      AuthService.isEmployeeLogedIn()
        .then((response) => (this.employeeLoggedIn = response.data))
        .catch((error) => {
          console.log(error);
        });
    },
  },

  beforeMount() {
    this.isUserLogedIn();
    this.isEmployeeLogedIn();
  },
};
</script>

<style>

html,body{
width: 100%;
height: 100%;
margin: 0;
padding: 0;
overflow-x: hidden;
min-width:100%; 
}

footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  
}
</style>
