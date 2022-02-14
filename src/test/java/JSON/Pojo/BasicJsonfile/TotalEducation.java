package JSON.Pojo.BasicJsonfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalEducation {
        @SerializedName("Elementary")
        @Expose
        private String elementary;
        @SerializedName("Board")
        @Expose
        private String board;
        @SerializedName("HighSchool")
        @Expose
        private String highSchool;
        @SerializedName("Graduation")
        @Expose
        private String graduation;

        public String getElementary() {
            return elementary;
        }

        public void setElementary(String elementary) {
            this.elementary = elementary;
        }

        public String getBoard() {
            return board;
        }

        public void setBoard(String board) {
            this.board = board;
        }

        public String getHighSchool() {
            return highSchool;
        }

        public void setHighSchool(String highSchool) {
            this.highSchool = highSchool;
        }

        public String getGraduation() {
            return graduation;
        }

        public void setGraduation(String graduation) {
            this.graduation = graduation;
        }
}
