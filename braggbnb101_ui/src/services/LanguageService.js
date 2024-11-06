import http from "../http-common"; 

class LanguageService {
  getAllLanguages(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/language/languages`, searchDTO);
  }

  get(languageId) {
    return this.getRequest(`/language/${languageId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/language?field=${matchData}`, null);
  }

  addLanguage(data) {
    return http.post("/language/addLanguage", data);
  }

  update(data) {
  	return http.post("/language/updateLanguage", data);
  }
  
  uploadImage(data,languageId) {
  	return http.postForm("/language/uploadImage/"+languageId, data);
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

export default new LanguageService();
