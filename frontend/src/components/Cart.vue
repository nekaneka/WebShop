<template>
  <!--Section: Block Content-->
  <section v-if="userLoggedIn" style="margin-left: 15px; margin-right: 15px">
    <!--Grid row-->
    <div class="row">
      <!--Grid column-->
      <div class="col-lg-8" style="margin-top: 100px; margin-bottom: 450px">
        <!-- Card -->
        <div class="card text-white bg-dark border-primary mb-3">
          <div class="card-body">
            {{/*onsole.log(items.length)*/}}

            <h5 class="mb-4">
              Cart (<span>{{ items.length }}</span> items)
            </h5>

            <div class="row mb-4" v-for="(item, i) in items" :key="i">
              <div class="col-md-5 col-lg-3 col-xl-3">
                <img
                  class="img-fluid w-100"
                  src="@/assets/cardImg.jpg"
                  alt="Sample"
                />
              </div>

              <div class="col-md-7 col-lg-9 col-xl-9">
                <div>
                  <div class="d-flex justify-content-between">
                    <div>
                      <h5>{{ item.title }}</h5>
                      <p class="mb-3 text-muted text-uppercase small">
                        {{ item.name }}
                      </p>
                      <p class="mb-2 text-muted text-uppercase small">
                        {{ item.itemId }}
                      </p>
                    </div>

                    <div>
                      <div>
                        <div class="input-group">
                          <button
                            href="#"
                            class="btn btn-primary"
                            type="button"
                            id="inputGroupFileAddon04"
                            v-on:click="decreaseItemQuantity(item.itemId)"
                          >
                            -
                          </button>
                          <input
                            type="text"
                            class="number"
                            min="0"
                            v-model="item.quantity"
                            style="text-align: center"
                          />
                          <button
                            href="#"
                            class="btn btn-primary"
                            type="button"
                            id="inputGroupFileAddon04"
                            v-on:click="increaseItemQuantity(item.itemId)"
                          >
                            +
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div>
                      <a
                        href="#!"
                        type="button"
                        class="card-link-secondary small text-uppercase mr-3"
                        ><i class="fas fa-trash-alt mr-1"></i> Remove item
                      </a>
                    </div>
                    <p class="mb-0">
                      <span
                        ><strong
                          >€{{ item.totalPrice.toFixed(2) }}</strong
                        ></span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Card -->
      </div>
      <!--Grid column-->

      <!--Grid column-->
      <div class="col-lg-4" style="margin-top: 100px; margin-bottom: 450px">
        <!-- Card -->
        <div class="card bg-dark border-primary mb-3">
          <div class="card-body">
            <h5 class="text-white mb-3">Shopping cost</h5>

            <ul class="list-group list-group-flush">
              <li
                class="list-group-item bg-dark text-white d-flex justify-content-between align-items-center border-0 px-0 pb-0"
              >
                Product cost
                <span>€{{ cartPriceValue() }}</span>
              </li>
              <li
                class="list-group-item bg-dark text-white d-flex justify-content-between align-items-center px-0"
              >
                Shipping
                <span>Free</span>
              </li>
              <li
                class="list-group-item bg-dark text-white d-flex justify-content-between align-items-center border-0 px-0 mb-3"
              >
                <div>
                  <strong>Total amount</strong>
                  <strong>
                    <p class="mb-0">(incl. VAT)</p>
                  </strong>
                </div>
                <span
                  ><strong>€{{ cartPriceValue() }}</strong></span
                >
              </li>
            </ul>

            <button
              type="button"
              class="btn btn-primary btn-block waves-effect waves-light"
              v-on:click="order()"
            >
              Order
            </button>
          </div>
        </div>
        <!-- Card -->
      </div>
      <!--Grid column-->
    </div>
    <!--Grid row-->
  </section>
  <!--Section: Block Content-->
  
<div v-else><SignInSignUp /></div>

</template>


<script>
import CartService from "@/services/CartService";
import AuthService from "@/services/AuthService";
import SignInSignUp from "@/components/SignIn_SignUp.vue";

export default {
  data() {
    return {
      items: [],
      userLoggedIn: false,
    };
  },

  components: {
    SignInSignUp,
  },

  methods: {
    getItems() {
      CartService.getCartItems()
        .then((response) => (this.items = response.data))
        .catch((error) => {
          console.log(error);
        });
    },

    increaseItemQuantity(id) {
      CartService.addCartItem(id)
        .then((response) => console.log(response),  this.getItems(), location.reload())
        .catch((error) => {
          console.log(error);
        });

    },

    decreaseItemQuantity(id) {
      CartService.removeCartItem(id)
        .then((response) => console.log(response),  this.getItems(), location.reload())
        .catch((error) => {
          console.log(error);
        });   
 
    },

    cartPriceValue() {
      var totalCartPrice = 0;
      var item;

      for (item of this.items) {
        totalCartPrice += item.totalPrice;
      }

      return totalCartPrice.toFixed(2);
    },

    isUserLogedIn() {
      AuthService.isUserLogedIn()
        .then((response) => (this.userLoggedIn = response.data))
        .catch((error) => {
          console.log(error);
        });
    },

    order(){
        CartService.order()
        .then((response) => console.log(response), location.reload())
        .catch((error) => {
          console.log(error);
        });

    }
  },

  beforeMount() {
    this.isUserLogedIn();

     setTimeout(() => { this.getItems() }, 400);
   
  },
};
</script>

