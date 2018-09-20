package edm.controller.payload;

import edm.model.user.User;

public class JwtAuthenticationResponse {

	private String accessToken;
    private String tokenType = "Bearer";
    private User loggedInUser;

    public JwtAuthenticationResponse(String accessToken, User loggedInUser) {
        this.accessToken = accessToken;
        this.loggedInUser = loggedInUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
