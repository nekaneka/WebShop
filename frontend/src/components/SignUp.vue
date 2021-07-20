<template>
  <section v-if="!userLoggedIn && !employeeLoggedIn">
    <div class="row" style="justify-content: center">
      <div class="col-lg-6" style="margin-top: 150px; margin-bottom: 450px">
        <div class="card text-white bg-dark border-primary">
          <div class="card-body">
            <h4 class="title text-center mt-4">Sign up</h4>
            <form class="form-box px-3">
              <div class="form-input">
                <span><i class="fa fa-user"></i></span>
                <input
                  type="text"
                  name=""
                  placeholder="Name"
                  tabindex="10"
                  required
                  v-model="name"
                />
              </div>

              <div class="form-input">
                <span><i class="fa fa-envelope"></i></span>
                <input
                  type="email"
                  name=""
                  placeholder="E-Mail"
                  tabindex="10"
                  required
                  v-model="email"
                />
              </div>

              <div class="form-input">
                <span><i class="fa fa-key"></i></span>
                <input
                  type="password"
                  name=""
                  placeholder="Password"
                  required
                  v-model="password"
                />
              </div>

              <div class="form-input">
                <span><i class="fa fa-key"></i></span>
                <input
                  type="password"
                  name=""
                  placeholder="Confirm password"
                  required
                />
              </div>

              <div class="text-center mb-3">
                <div class="custom-control custom-checkbox">
                  <input
                    type="checkbox"
                    class="custom-control-input"
                    id="cb1"
                    name=""
                  />
                  <label class="custom-control-label text-center" for="cb1"
                    >Remember credentials</label
                  >
                </div>
              </div>

              <div class="row" style="justify-content: center">
                <div class="col-lg-6 mb-5">
                  <button :disabled="!buttonActive()" type="button" class="btn btn-primary btn-block" v-on:click="register()">
                    Sign up
                  </button>
                </div>
              </div>
              <div class="text-center mb-2">
                Have an account?
                <router-link class="register-link active" to="/login"
                  >Log in</router-link
                >
              </div>
            </form>
          </div>
          <img src="@/assets/SignUpImg.jpg" class="card-img-left" alt="..." />
        </div>
      </div>
    </div>
  </section>

  <div v-else>
    <LoggedIn />
  </div>


</template>



<script>

import AuthService from "@/services/AuthService";
import LoggedIn from "@/components/LoggedIn.vue";

export default {
  data(){
    return{
      name:'',
      email:'',
      password:'',
      userLoggedIn: false,
      employeeLoggedIn: false,
    };
  },


  components: {
    LoggedIn,
  },


  methods:{

    register(){

        AuthService.register(this.email,this.password, this.name)
        .then((response) => console.log(response), window.location.href = '/#/login')
        .catch((error) => {
          console.log(error);
        });

    },

    buttonActive(){

      if(this.name=='' || this.email=='' || this.password=='') return false;
      else return true;
    },

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
    }

  },

    beforeMount() {
    this.isUserLogedIn();
    this.isEmployeeLogedIn();
  },
}
</script>




<style scoped>
.card {
  flex-direction: row;
  border-radius: 20px !important;
  height: 550px;
}

.card img {
  width: 50%;
  border-top-right-radius: 20px !important;
  border-bottom-right-radius: 20px !important;
}

.card-body {
  padding: 2rem;
}

.title {
  margin-bottom: 2rem;
}

.form-input {
  position: relative;
}

.form-input input {
  width: 100%;
  height: 45px;
  padding-left: 40px;
  margin-bottom: 20px;
  box-sizing: border-box;
  box-shadow: none;
  border: 1px solid #00000020;
  border-radius: 50px;
  outline: none;
  color: white;
  background: transparent;
  border-color: #007bff;
}

.form-input span {
  position: absolute;
  top: 10px;
  padding-left: 15px;
  color: #007bff;
}

.form-input input:focus::placeholder {
  color: #454b69;
}

.forget-link,
.register-link {
  color: #007bff;
  font-weight: bold;
}

.forget-link:hover,
.register-link:hover {
  color: #0069d9;
  text-decoration: none;
}
</style>