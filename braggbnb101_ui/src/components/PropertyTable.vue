
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Propertys</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalPropertys = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalPropertys">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Property</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="PropertyId" type="text" placeholder="Enter PropertyId" v-model="propertyToAdd.propertyId"></base-input>
  <base-input label="Address" type="text" placeholder="Enter Address" v-model="propertyToAdd.address"></base-input>
  <base-input label="City" type="text" placeholder="Enter City" v-model="propertyToAdd.city"></base-input>
  <base-input label="State" type="text" placeholder="Enter State" v-model="propertyToAdd.state"></base-input>
  <base-input label="ZipCode" type="text" placeholder="Enter ZipCode" v-model="propertyToAdd.zipCode"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="propertys" :row-key="record => record.PropertyId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <PropertyPictureView :propertys="propertys" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import PropertyService from "../services/PropertyService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import PropertyPictureView from './PropertyPictureView.vue';


const propertysColumns = [
  "propertyId",
  "year",
  "date",
  "competitionId",
  "propertyId"
]

export default {
  props: {
    propertys: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    PropertyPictureView
  },

  data() {
    return {
      modalPropertys: false,
        isTableView: true,

      columns: [
        {
          title: 'Property Id',
		dataIndex: 'propertyId',
          visible: true,
          scopedSlots: { customRender: 'propertyId' },
          sorter: true
          //sorter: (a, b) => a.propertyId - b.propertyId,
          //sorter: (a, b) => a.propertyId.localeCompare(b.propertyId),
        },
        {
          title: 'Address',
		dataIndex: 'address',
          visible: true,
          scopedSlots: { customRender: 'address' },
          sorter: true
          //sorter: (a, b) => a.address - b.address,
          //sorter: (a, b) => a.address.localeCompare(b.address),
        },
        {
          title: 'City',
		dataIndex: 'city',
          visible: true,
          scopedSlots: { customRender: 'city' },
          sorter: true
          //sorter: (a, b) => a.city - b.city,
          //sorter: (a, b) => a.city.localeCompare(b.city),
        },
        {
          title: 'State',
		dataIndex: 'state',
          visible: true,
          scopedSlots: { customRender: 'state' },
          sorter: true
          //sorter: (a, b) => a.state - b.state,
          //sorter: (a, b) => a.state.localeCompare(b.state),
        },
        {
          title: 'Zip Code',
		dataIndex: 'zipCode',
          visible: true,
          scopedSlots: { customRender: 'zipCode' },
          sorter: true
          //sorter: (a, b) => a.zipCode - b.zipCode,
          //sorter: (a, b) => a.zipCode.localeCompare(b.zipCode),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} propertys`,
      },

      propertys: [],
      propertyToAdd: {},

      propertysTable: {
        columns: [...propertysColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'propertyId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderPropertysTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let propertysTableData = [];
      for (let i = 0; i < this.propertys.length; i++) {
        propertysTableData.push({
          id: i,
          propertyId: this.propertys[i].propertyId,
          year: this.propertys[i].year,
          date: this.propertys[i].date,
          competitionId: this.propertys[i].competitionId,
          propertyId: this.propertys[i].propertyId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-propertys',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    routingToHostDetail(id) {
      this.$router.push({ name: 'HostDetail', params: { hostId: id.toString() }})
    },
    routingToGuestDetail(id) {
      this.$router.push({ name: 'GuestDetail', params: { guestId: id.toString() }})
    },
    routingToPropertyDetail(id) {
      this.$router.push({ name: 'PropertyDetail', params: { propertyId: id.toString() }})
    },
    routingToRoomDetail(id) {
      this.$router.push({ name: 'RoomDetail', params: { roomId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToReviewDetail(id) {
      this.$router.push({ name: 'ReviewDetail', params: { reviewId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToAmenityDetail(id) {
      this.$router.push({ name: 'AmenityDetail', params: { amenityId: id.toString() }})
    },
    routingToMessageDetail(id) {
      this.$router.push({ name: 'MessageDetail', params: { messageId: id.toString() }})
    },
    routingToWishlistDetail(id) {
      this.$router.push({ name: 'WishlistDetail', params: { wishlistId: id.toString() }})
    },
    routingToLocationDetail(id) {
      this.$router.push({ name: 'LocationDetail', params: { locationId: id.toString() }})
    },
    routingToPropertyImageDetail(id) {
      this.$router.push({ name: 'PropertyImageDetail', params: { propertyImageId: id.toString() }})
    },
    routingToCancellationPolicyDetail(id) {
      this.$router.push({ name: 'CancellationPolicyDetail', params: { cancellationPolicyId: id.toString() }})
    },
    routingToTaxDetail(id) {
      this.$router.push({ name: 'TaxDetail', params: { taxId: id.toString() }})
    },
    routingToDiscountDetail(id) {
      this.$router.push({ name: 'DiscountDetail', params: { discountId: id.toString() }})
    },
    routingToSecurityDepositDetail(id) {
      this.$router.push({ name: 'SecurityDepositDetail', params: { securityDepositId: id.toString() }})
    },
    routingToPromotionDetail(id) {
      this.$router.push({ name: 'PromotionDetail', params: { promotionId: id.toString() }})
    },
    routingToNotificationDetail(id) {
      this.$router.push({ name: 'NotificationDetail', params: { notificationId: id.toString() }})
    },
    routingToLanguageDetail(id) {
      this.$router.push({ name: 'LanguageDetail', params: { languageId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForPropertysChanged', this.searchQuery);
		//this.renderPropertysTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalPropertys = false;

      const currentDate = new Date().getTime();
      this.propertyToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.propertyToAdd);
      console.log(jsonData);
      
      const res = await PropertyService.addProperty(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderPropertysTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
