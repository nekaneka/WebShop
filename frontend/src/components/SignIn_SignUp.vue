<template>
  <section>
    <div class="row" style="justify-content: center">
      <div class="col-lg-6" style="margin-top: 150px; margin-bottom: 450px">
        <div class="card text-white bg-dark border-primary">
                      <img
            src="@/assets/NotLoggedImg.jpg"
            class="card-img-top"
            alt="..."
          />
          <div class="card-body ">

            <h5 class="title text-center mt-4">
              Please log in or sign up to get access.
            </h5>
            
              <div class="text-center mb-2">
                <a href="#" class="forget-link">Forgot password.</a>
              </div>

              <div class="text-center mb-2">
                Still no account?
                <router-link class="register-link active" to="/register"
                  >Register here</router-link
                >
              </div>

              <div class="text-center mb-2">
                Already have an account?
                <router-link class="register-link active" to="/login"
                  >Log in</router-link
                >             
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.card {

  flex-direction: column;
  border-radius: 20px !important;
  height: 550px;
  
  
}

.card img {
  height: 55%;
  border-top-right-radius: 20px !important;
  border-top-left-radius: 20px !important;

}

.card-body {
  padding: 2rem;
}

.title {
  margin-bottom: 2rem;
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