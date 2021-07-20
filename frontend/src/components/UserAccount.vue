<template>
  <!--Section: Block Content-->
  <section v-if="userLoggedIn" style="margin-left: 15px; margin-right: 15px">
    <!--Grid row-->
    <div class="row">
      <!--Grid column-->
      <div class="col-lg-3" style="margin-top: 100px; margin-bottom: 450px">
        <!-- Card -->
        <div class="card text-white bg-dark border-primary mb-3">
          <div class="card-body">
            <nav id="sidebar">
              <div style="color: white; text-align: center">
                <h3>Your Account</h3>
              </div>
              <ul style="color: white">
                <li>
                  <a v-on:click="showTable(0), getUserOrders()" href="#">
                    <span class="d-none d-sm-inline">
                      <i class="fas fa-luggage-cart"></i> Orders</span
                    >
                  </a>
                </li>
                <li>
                  <a v-on:click="showTable(1)" href="#">
                    <span class="d-none d-sm-inline"
                      ><i class="fas fa-user-edit"></i> Profile</span
                    >
                  </a>
                </li>

                <li>
                  <a v-on:click="showTable(2)" href="#">
                    <span class="d-none d-sm-inline">
                      <i class="fas fa-star"></i> Whishlist</span
                    >
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- Card -->
      </div>
      <!--Grid column-->

      <!--Grid column-->
      <div class="col-lg-8" style="margin-top: 100px; margin-bottom: 450px">
        <!-- Card -->
        <div class="card bg-dark border-primary mb-3">
          <div class="card-body">
            <div style="text-align: center; margin-bottom: 20px">
                            <table
              class="table table-hover table-dark"
              v-if="showOrders"
            >
              <thead class="thead-primary">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Order ID</th>
                  <th scope="col">Item name</th>
                  <th scope="col">Items bought</th>
                  <th scope="col">Item price</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(item, index) in orders"
                  :key="index"
                >
                  <th scope="row">{{ index+1 }}</th>
                    <td>{{item.orderId}}</td>
                  <td>{{ item.itemName }}</td>
                  <td>{{ item.quantity }}</td>
                   <td>{{ item.price }}</td>     
                </tr>
              </tbody>
            </table>
            </div>
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
import SignInSignUp from "@/components/SignIn_SignUp.vue";
import AuthService from "@/services/AuthService";
import AccountService from "@/services/AccountService";

export default {
  components: {
    SignInSignUp,
 
  },

  data() {
    return {
      orders: [],
      userLoggedIn: false,
         showOrders:false
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

    getUserOrders() {
      AccountService.getUserOrders()
        .then((response) => (this.orders = response.data))
        .catch((error) => {
          console.log(error);
        });
    },

    showTable(id){

        if(id==0) this.showOrders=true;
         else this.showOrders=false;

    }
  },
  beforeMount() {
    this.isUserLogedIn();
  },
};
</script>

<style scoped>
a,
a:hover,
a:focus {
  color: inherit;
  text-decoration: none;
  transition: all 0.3s;
}

#sidebar ul li a {
  padding: 10px;
  font-size: 1.1em;
  display: block;
}

#sidebar ul li a:hover {
  color: #fff;
  background: #0066ff;
}

a[data-toggle="collapse"] {
  position: relative;
}

.dropdown-toggle::after {
  display: block;
  position: absolute;
  top: 50%;
  right: 20%;
  transform: translateY(-50%);
}
ul ul a {
  font-size: 0.9em !important;
  padding-left: 30px !important;
}

ul li {
  list-style-type: none;
}

.table-hover tbody tr:hover td,
.table-hover tbody tr:hover th {
  background: #0066ff;
}
</style>