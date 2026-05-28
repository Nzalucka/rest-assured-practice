package spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowPlaylistBody {
        @JsonProperty("public")
        private boolean isPublic;

        public FollowPlaylistBody(boolean isPublic) {
            this.isPublic = isPublic;
        }

        public boolean isPublic() { return isPublic; }
}
