import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageNav {

    private final int DEFAULT_NAV_SIZE = 5;

    private int navSize;
    private int navStartPageNumber;
    private int navEndPageNumber;

    private boolean hasPreviousBlock;
    private boolean hasNextBlock;


    public PageNav(Page page) {
        makeNav(page, DEFAULT_NAV_SIZE, false);
    }

    public PageNav(Page page, boolean center) {
        makeNav(page, DEFAULT_NAV_SIZE, center);
    }

    public PageNav(Page page, int navSize) {
        makeNav(page, navSize, false);
    }

    public PageNav(Page page, int navSize, boolean center) {
        makeNav(page, navSize, center);
    }

    private void makeNav(Page page, int navSize, boolean center) {
        this.navSize = navSize;

        if (center)
            this.navStartPageNumber = Math.max(0, page.getNumber() - navSize / 2);
        else if (page.getNumber() > navSize) {
            this.navStartPageNumber = (page.getNumber() / navSize) * navSize + 1;
        }

        this.navEndPageNumber = Math.min(page.getTotalPages() - 1, this.navStartPageNumber + navSize - 1);

        if (this.navStartPageNumber > 0)
            this.hasPreviousBlock = true;
        if (this.navEndPageNumber < page.getTotalPages())
            this.hasNextBlock = true;
    }
}
