<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <propertyImage-table
            v-if="propertyImages && propertyImages.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:propertyImages="propertyImages"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-property-images="getAllPropertyImages"
             >

            </propertyImage-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PropertyImageTable from "@/components/PropertyImageTable";
import PropertyImageService from "../services/PropertyImageService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PropertyImageTable,
  },
  data() {
    return {
      propertyImages: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPropertyImages(sortBy='propertyImageId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PropertyImageService.getAllPropertyImages(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.propertyImages.length) {
					this.propertyImages = response.data.propertyImages;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching propertyImages:", error);
        }
        
      } catch (error) {
        console.error("Error fetching propertyImage details:", error);
      }
    },
  },
  mounted() {
    this.getAllPropertyImages();
  },
  created() {
    this.$root.$on('searchQueryForPropertyImagesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPropertyImages();
    })
  }
};
</script>
<style></style>
