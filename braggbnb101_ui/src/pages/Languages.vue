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
            <language-table
            v-if="languages && languages.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:languages="languages"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-languages="getAllLanguages"
             >

            </language-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LanguageTable from "@/components/LanguageTable";
import LanguageService from "../services/LanguageService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LanguageTable,
  },
  data() {
    return {
      languages: [],
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
    async getAllLanguages(sortBy='languageId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LanguageService.getAllLanguages(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.languages.length) {
					this.languages = response.data.languages;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching languages:", error);
        }
        
      } catch (error) {
        console.error("Error fetching language details:", error);
      }
    },
  },
  mounted() {
    this.getAllLanguages();
  },
  created() {
    this.$root.$on('searchQueryForLanguagesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLanguages();
    })
  }
};
</script>
<style></style>
