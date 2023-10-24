/** @jsxImportSource @emotion/react */
import { searchBarDivStyle, searchBarIconStyle, searchBarInputStyle } from "./SearchBar.style";

export default function SearchBar() {

    return (
        <div css={searchBarDivStyle}>
            <div css={searchBarIconStyle}>
                <i>@</i>
            </div>
            <input css={searchBarInputStyle} />
        </div>
    );

}
