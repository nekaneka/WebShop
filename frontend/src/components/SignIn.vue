<template>
  <section v-if="!userLoggedIn && !employeeLoggedIn">
    <div class="row" style="justify-content: center">
      <div class="col-lg-6" style="margin-top: 150px; margin-bottom: 450px">
        <div class="card text-white bg-dark border-primary">
          <img src="@/assets/LoginImg.jpg" class="card-img-left" alt="..." />
          <div class="card-body" style="margin-top: 50px">
            <h4 class="title text-center mt-4">Log in to your account</h4>
            <form class="form-box px-3">
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

              <div class="mb-3 text-center">
                <div class="custom-control custom-checkbox">
                  <input
                    type="checkbox"
                    class="custom-control-input"
                    id="cb1"
                    name=""
                  />
                  <label class="custom-control-label text-center" for="cb1"
                    >Remember login</label
                  >
                </div>
              </div>

              <div class="row" style="justify-content: center">
                <div class="col-lg-6 mb-5">
                  <button
                    :disabled="!buttonActive()"
                    type="button"
                    class="btn btn-primary btn-block"
                    v-on:click="login()"
                  >
                    Log in
                  </button>
                </div>
              </div>

              <div class="text-center">
                <a href="#" class="forget-link">Forgot password.</a>
              </div>

              <div class="text-center mb-2">
                Still no account?
                <router-link class="register-link active" to="/register"
                  >Register here</router-link
                >
              </div>
            </form>
          </div>
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
  data() {
    return {
      email: "",
      password: "",
      userLoggedIn: false,
      employeeLoggedIn: false,
    };
  },

  components: {
    LoggedIn,
  },

  methods: {
    login() {
      AuthService.login(this.email, this.password)
        .then((response) => console.log(response), (window.location.href = "/"))
        .catch((error) => {
          console.log(error);
        });
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
    },

    buttonActive() {
      if (this.email == "" || this.password == "") return false;
      else return true;
    },
  },

  beforeMount() {
    this.isUserLogedIn();
    this.isEmployeeLogedIn();
  },
};
</script>

<style scoped>
.card {
  flex-direction: row;
  border-radius: 20px !important;
  height: 550px;
}

.card img {
  width: 50%;
  border-top-left-radius: 20px !important;
  border-bottom-left-radius: 20px !important;
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