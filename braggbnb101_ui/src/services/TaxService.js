import http from "../http-common"; 

class TaxService {
  getAllTaxs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/tax/taxs`, searchDTO);
  }

  get(taxId) {
    return this.getRequest(`/tax/${taxId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/tax?field=${matchData}`, null);
  }

  addTax(data) {
    return http.post("/tax/addTax", data);
  }

  update(data) {
  	return http.post("/tax/updateTax", data);
  }
  
  uploadImage(data,taxId) {
  	return http.postForm("/tax/uploadImage/"+taxId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new TaxService();
