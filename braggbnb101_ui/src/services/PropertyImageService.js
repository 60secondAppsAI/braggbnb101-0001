import http from "../http-common"; 

class PropertyImageService {
  getAllPropertyImages(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/propertyImage/propertyImages`, searchDTO);
  }

  get(propertyImageId) {
    return this.getRequest(`/propertyImage/${propertyImageId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/propertyImage?field=${matchData}`, null);
  }

  addPropertyImage(data) {
    return http.post("/propertyImage/addPropertyImage", data);
  }

  update(data) {
  	return http.post("/propertyImage/updatePropertyImage", data);
  }
  
  uploadImage(data,propertyImageId) {
  	return http.postForm("/propertyImage/uploadImage/"+propertyImageId, data);
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

export default new PropertyImageService();
