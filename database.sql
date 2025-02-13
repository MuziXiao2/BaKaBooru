CREATE TABLE image
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE atlas
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE source
(
    name VARCHAR(255) PRIMARY KEY,
    type VARCHAR(50) NOT NULL
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE source_atlas
(
    source_name VARCHAR(255),
    atlas_id    INT,
    PRIMARY KEY (source_name, atlas_id),
    FOREIGN KEY (source_name) REFERENCES Source (name)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (atlas_id) REFERENCES Atlas (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE atlas_image
(
    atlas_id INT,
    image_id INT,
    PRIMARY KEY (atlas_id, image_id),
    FOREIGN KEY (atlas_id) REFERENCES Atlas (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (image_id) REFERENCES Image (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
