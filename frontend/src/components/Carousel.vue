<template>
  <div
    id="carouselExampleIndicators"
    class="carousel slide"
    data-bs-ride="carousel"
    data-interval="2000"
  >
    <ol class="carousel-indicators">
      <li
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="0"
        class="active"
      ></li>
      <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
      <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
      <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"></li>
    </ol>
    <div class="carousel-inner" style="height: 550px">
      <div class="carousel-item active">
        <img
          src="@/assets/carouselImg/smartphone.jpg"
          class="d-block w-100"
          alt="..."
        />
      </div>
      <div class="carousel-item">
        <img
          src="@/assets/carouselImg/desktop.jpg"
          class="d-block w-100"
          alt="..."
        />
      </div>
      <div class="carousel-item">
        <img
          src="@/assets/carouselImg/laptop.jpg"
          class="d-block w-100"
          alt="..."
        />
      </div>
      <div class="carousel-item">
        <img
          src="@/assets/carouselImg/zubehoer.jpg"
          class="d-block w-100"
          alt="..."
        />
      </div>
    </div>
    <a
      class="carousel-control-prev"
      href="#carouselExampleIndicators"
      role="button"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden"></span>
    </a>
    <a
      class="carousel-control-next"
      href="#carouselExampleIndicators"
      role="button"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden"></span>
    </a>
  </div>
</template>


<style scoped >

.item img {
  width:auto;
  height: auto;
}
</style>





