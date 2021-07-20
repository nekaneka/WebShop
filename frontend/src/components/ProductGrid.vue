<template>
  <div>
    <div class="row row-cols-1 row-cols-md-4 g-4" style="margin: 10px">
      <div class="col" v-for="(product, i) in pageProducts" :key="i">
        <div
          class="card text-white bg-dark ms-3 border-primary"
          style="max-width: 18rem"
        >
          <img src="@/assets/cardImg.jpg" class="card-img-top" alt="..." />
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text">{{ product.description }}</p>
            <p class="card-text">{{ product.price }}€</p>
            <button
              class="btn btn-primary"
              type="button"
              data-container="body"
              data-toggle="popover"
              data-placement="top"
              data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus."
              v-on:click="addItemToCart(product.id)"
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="d-flex justify-content-center">
      <nav
        aria-label="Page navigation"
        style="margin: 35px; margin-top: 20px; margin-bottom: 450px"
      >
        <ul class="pagination pagination-circle">
          <li class="page-item active">
            <a
              class="page-link"
              href="#"
              aria-label="Previous"
              v-on:click="
                setPageNum(page - 1);
                setPageItems();
              "
            >
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>

          <li
            class="page-item"
            v-for="index in Math.ceil(products.length / itemsPerPage)"
            :key="index"
          >
            <a
              class="page-link"
              href="#"
              v-on:click="
                setPageNum(index - 1);
                setPageItems();
              "
              >{{ index }}</a
            >
          </li>

          <li class="page-item active">
            <a
              class="page-link"
              href="#"
              aria-label="Next"
              v-on:click="
                setPageNum(page + 1);
                setPageItems();
              "
            >
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";
import CartService from "@/services/CartService";

export default {
  data() {
    return {
      products: [],
      pageProducts: [],
      page: 0,
      itemsPerPage: 12,
    };
  },

  methods: {
    getProducts() {
      ProductService.getProducts()
        .then((response) => (this.products = response.data))
        .catch((error) => {
          console.log(error);
        });
    },

    setPageItems() {
      this.pageProducts = this.products.slice(
        this.page * this.itemsPerPage,
        this.page * this.itemsPerPage + this.itemsPerPage
      );
    },

    setPageNum(pageNum) {
      if (pageNum == Math.ceil(this.products.length / this.itemsPerPage))
        this.page = Math.ceil(this.products.length / this.itemsPerPage - 1);
      else if (pageNum < 0) this.page = 0;
      else this.page = pageNum;
    },

    addItemToCart(id) {
      CartService.addCartItem(id)
        .then((response) => console.log(response))
        .catch((error) => {
          console.log(error);
        });
    },
  },

  beforeMount() {
    this.getProducts();
   
    setTimeout(() => {   this.setPageItems() }, 800);
  },
};
</script>


