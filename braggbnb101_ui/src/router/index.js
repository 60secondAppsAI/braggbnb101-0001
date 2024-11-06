import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Hosts from  '@/pages/Hosts.vue';
import HostDetail from  '@/pages/HostDetail.vue';
import Guests from  '@/pages/Guests.vue';
import GuestDetail from  '@/pages/GuestDetail.vue';
import Propertys from  '@/pages/Propertys.vue';
import PropertyDetail from  '@/pages/PropertyDetail.vue';
import Rooms from  '@/pages/Rooms.vue';
import RoomDetail from  '@/pages/RoomDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Amenitys from  '@/pages/Amenitys.vue';
import AmenityDetail from  '@/pages/AmenityDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Wishlists from  '@/pages/Wishlists.vue';
import WishlistDetail from  '@/pages/WishlistDetail.vue';
import Locations from  '@/pages/Locations.vue';
import LocationDetail from  '@/pages/LocationDetail.vue';
import PropertyImages from  '@/pages/PropertyImages.vue';
import PropertyImageDetail from  '@/pages/PropertyImageDetail.vue';
import CancellationPolicys from  '@/pages/CancellationPolicys.vue';
import CancellationPolicyDetail from  '@/pages/CancellationPolicyDetail.vue';
import Taxs from  '@/pages/Taxs.vue';
import TaxDetail from  '@/pages/TaxDetail.vue';
import Discounts from  '@/pages/Discounts.vue';
import DiscountDetail from  '@/pages/DiscountDetail.vue';
import SecurityDeposits from  '@/pages/SecurityDeposits.vue';
import SecurityDepositDetail from  '@/pages/SecurityDepositDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Languages from  '@/pages/Languages.vue';
import LanguageDetail from  '@/pages/LanguageDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/users',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/hosts',
		name: 'Hosts',
		layout: DefaultLayout,
		component: Hosts,
	},
	{
	    path: '/host/:hostId', 
	    name: 'HostDetail',
		layout: DefaultLayout,
	    component: HostDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/guests',
		name: 'Guests',
		layout: DefaultLayout,
		component: Guests,
	},
	{
	    path: '/guest/:guestId', 
	    name: 'GuestDetail',
		layout: DefaultLayout,
	    component: GuestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/propertys',
		name: 'Propertys',
		layout: DefaultLayout,
		component: Propertys,
	},
	{
	    path: '/property/:propertyId', 
	    name: 'PropertyDetail',
		layout: DefaultLayout,
	    component: PropertyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/rooms',
		name: 'Rooms',
		layout: DefaultLayout,
		component: Rooms,
	},
	{
	    path: '/room/:roomId', 
	    name: 'RoomDetail',
		layout: DefaultLayout,
	    component: RoomDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/amenitys',
		name: 'Amenitys',
		layout: DefaultLayout,
		component: Amenitys,
	},
	{
	    path: '/amenity/:amenityId', 
	    name: 'AmenityDetail',
		layout: DefaultLayout,
	    component: AmenityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/wishlists',
		name: 'Wishlists',
		layout: DefaultLayout,
		component: Wishlists,
	},
	{
	    path: '/wishlist/:wishlistId', 
	    name: 'WishlistDetail',
		layout: DefaultLayout,
	    component: WishlistDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/locations',
		name: 'Locations',
		layout: DefaultLayout,
		component: Locations,
	},
	{
	    path: '/location/:locationId', 
	    name: 'LocationDetail',
		layout: DefaultLayout,
	    component: LocationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/propertyImages',
		name: 'PropertyImages',
		layout: DefaultLayout,
		component: PropertyImages,
	},
	{
	    path: '/propertyImage/:propertyImageId', 
	    name: 'PropertyImageDetail',
		layout: DefaultLayout,
	    component: PropertyImageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellationPolicys',
		name: 'CancellationPolicys',
		layout: DefaultLayout,
		component: CancellationPolicys,
	},
	{
	    path: '/cancellationPolicy/:cancellationPolicyId', 
	    name: 'CancellationPolicyDetail',
		layout: DefaultLayout,
	    component: CancellationPolicyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/taxs',
		name: 'Taxs',
		layout: DefaultLayout,
		component: Taxs,
	},
	{
	    path: '/tax/:taxId', 
	    name: 'TaxDetail',
		layout: DefaultLayout,
	    component: TaxDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/discounts',
		name: 'Discounts',
		layout: DefaultLayout,
		component: Discounts,
	},
	{
	    path: '/discount/:discountId', 
	    name: 'DiscountDetail',
		layout: DefaultLayout,
	    component: DiscountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/securityDeposits',
		name: 'SecurityDeposits',
		layout: DefaultLayout,
		component: SecurityDeposits,
	},
	{
	    path: '/securityDeposit/:securityDepositId', 
	    name: 'SecurityDepositDetail',
		layout: DefaultLayout,
	    component: SecurityDepositDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/languages',
		name: 'Languages',
		layout: DefaultLayout,
		component: Languages,
	},
	{
	    path: '/language/:languageId', 
	    name: 'LanguageDetail',
		layout: DefaultLayout,
	    component: LanguageDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
