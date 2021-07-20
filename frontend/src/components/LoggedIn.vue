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
              You're already logged in.
            </h5>
        

              <div class="text-center mb-2">
               Please log out to switch account.      
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