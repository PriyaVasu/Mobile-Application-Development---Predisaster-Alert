def get_page(url):
    try:
        if url == "http://www.imd.gov.in":
            return ('<a href="http://www.imd.gov.in/pages/city_weather_main.php">City Weather</a>')
        elif url == "http://www.imd.gov.in/pages/city_weather_main.php":
            return ('<a href="http://14.139.247.11/citywx/menu.php" target=_blank><font style='
                    '"color:#003300; font:cursive; font-size:14px">Statewise List of station</font></a>')
        elif url == "http://14.139.247.11/citywx/menu.php":
            return ('<li><a href="##">Andhra Pradesh</a><ul>'
                    '<li><a href="city_weather.php?id=43237" target=mainframe>Anantapur</a></li>'
                    '<li><a href="city_weather.php?id=43241" target=mainframe>Cuddappah</a></li>'
                    '<li><a href="city_weather.php?id=43189" target=mainframe>Kakinada</a></li>'
                    '<li><a href="city_weather.php?id=43105" target=mainframe>Kalingapatnam</a></li>'
                    '<li><a href="city_weather.php?id=43213" target=mainframe>Kurnool</a></li>'
                    '<li><a href="city_weather.php?id=43185" target=mainframe>Machilipatnam</a></li>'
                    '<li><a href="city_weather.php?id=43245" target=mainframe>Nellore</a></li>'
                    '<li><a href="city_weather.php?id=43221" target=mainframe>Ongole</a></li>'
                    '<li><a href="city_weather.php?id=43275" target=mainframe>Tirupathi</a></li>'
                    '<li><a href="city_weather.php?id=43181" target=mainframe>Vijayawada</a></li>'
                    '<li><a href="city_weather.php?id=43149" target=mainframe>Visakhapatnam</a></li>'
                    '</li></ul>'
                    '<li><a href="##">Arunachal Pradesh</a><ul>'
                    '<li><a href="city_weather.php?id=30002" target=mainframe>Anni</a></li>'
                    '<li><a href="city_weather.php?id=42308" target=mainframe>Itanagar</a></li>'
                    '<li><a href="city_weather.php?id=42220" target=mainframe>Passighat</a></li>'
                    '<li><a href="city_weather.php?id=30001" target=mainframe>Tawang</a></li>'
                    '</li></ul>')
    except:
        return ""
    return ""

def get_next_target(page):
    start_link = page.find('<a href=')
    if start_link == -1:
        return None, 0
    start_quote = page.find('"', start_link)
    end_quote = page.find('"', start_quote + 1)
    url = page[start_quote + 1:end_quote]
    return url, end_quote

def union(p,q):
    for e in q:
        if e not in p:
            p.append(e)

def get_all_links(page):
    links = []
    while True:
        url,endpos = get_next_target(page)
        if url:
            links.append(url)
            page = page[endpos:]
        else:
            break
    return links

def crawl_web(seed,max_depth):    
    tocrawl = [seed]
    crawled = []
    next_depth = []
    depth = 0
    while tocrawl and depth <= max_depth:
        page = tocrawl.pop()
        if page not in crawled:
            union(next_depth, get_all_links(get_page(page)))
            crawled.append(page)
        if not tocrawl:
            tocrawl, next_depth = next_depth, []
            depth = depth + 1
    return crawled
